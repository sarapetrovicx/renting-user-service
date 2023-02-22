package com.raf.restdemo.service.impl;

import com.raf.restdemo.comunication.NotificationDto;
import com.raf.restdemo.domain.Client;
import com.raf.restdemo.domain.UserStatus;
import com.raf.restdemo.dto.*;
import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.mapper.ClientMapper;
import com.raf.restdemo.repository.ClientRepository;
import com.raf.restdemo.repository.UserStatusRepository;
import com.raf.restdemo.security.activationCode.ActivationCodeGenerator;
import com.raf.restdemo.security.service.TokenService;
import com.raf.restdemo.service.ClientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private UserStatusRepository userStatusRepository;

    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String notification;

    public ClientServiceImpl(TokenService tokenService, ClientRepository clientRepository, ClientMapper clientMapper, UserStatusRepository userStatusRepository,
                             JmsTemplate jmsTemplate, MessageHelper messageHelper,@Value("notification_destination") String notification) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.userStatusRepository = userStatusRepository;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.notification = notification;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::userToUserDto);
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).get();
        return clientMapper.userToUserDto(client);
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {

        //save client
        Client client = clientMapper.userCreateDtoToUser(clientCreateDto);
        client.setActive(true);
        client.setConfirmed(false);
        ActivationCodeGenerator activationCodeGenerator = new ActivationCodeGenerator();
        client.setActivationCode(activationCodeGenerator.generateCode());
        clientRepository.save(client);

        //send activation notification
        NotificationDto notification = new NotificationDto();
        notification.setRecever(clientCreateDto.getEmail());
        notification.setType("Activation");
        String param = clientCreateDto.getFirstName() + ", " + clientCreateDto.getLastName() + ", client, " +
                clientCreateDto.getEmail() + ", " + clientCreateDto.getUsername() + ", " +
                "localhost:8080/api/client/confirmRegistration/"+client.getActivationCode();
        notification.setParameters(param);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));

        return clientMapper.userToUserDto(client);
    }

    @Override
    public ClientDto changeRentDays(ClientRentDaysDto clientRentDaysDto) {
        Client client = clientRepository.findById(clientRentDaysDto.getClientId()).get();
        client.setSumOfRentalDays(client.getSumOfRentalDays()+ clientRentDaysDto.getDays());
        clientRepository.save(client);
        return  clientMapper.userToUserDto(client);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active client for specified credentials
        Client client = clientRepository
                .findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Client with username: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        if(client.isActive() && client.isConfirmed()) {
            //Create token payload
            Claims claims = Jwts.claims();
            claims.put("id", client.getId());
            claims.put("role", client.getRole());
            //Generate token
            return new TokenResponseDto(tokenService.generate(claims));
        } else{
            return null;
        }
    }

    @Override
    public ClientDto edit(Long id, ClientCreateDto clientCreateDto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String
                .format("User with email: %s and password: %s not found.", clientCreateDto.getEmail(),
                        clientCreateDto.getPassword())));
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setPassportNum(clientCreateDto.getPassportNum());
        clientRepository.save(client);


        //send notification
        NotificationDto notification = new NotificationDto();
        notification.setRecever(clientCreateDto.getEmail());
        notification.setType("Profile_change");
        String param = id + ", " + clientCreateDto.getUsername() + ", " + clientCreateDto.getPassword() + ", " +
                clientCreateDto.getEmail() + ", " + clientCreateDto.getFirstName() + ", " +
                clientCreateDto.getFirstName()  + ", " + clientCreateDto.getLastName();
        notification.setParameters(param);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));

        return clientMapper.userToUserDto(client);
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        Client user = clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        List<UserStatus> userStatusList = userStatusRepository.findAll();
        //get discount
        Integer discount = userStatusList.stream()
                .filter(userStatus -> userStatus.getMaxNumberOfRentalDays() >= user.getSumOfRentalDays()
                        && userStatus.getMinNumberOfRentalDays() <= user.getSumOfRentalDays())
                .findAny()
                .get()
                .getDiscount();
        String rank = userStatusList.stream()
                .filter(userStatus -> userStatus.getMaxNumberOfRentalDays() >= user.getSumOfRentalDays()
                        && userStatus.getMinNumberOfRentalDays() <= user.getSumOfRentalDays())
                .findAny()
                .get()
                .getRank();

        return new DiscountDto(discount, rank);
    }

    @Override
    public ClientDto changeActivity(Long id, boolean activity) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String
                .format("User with id: %d not found.", id)));
        client.setActive(activity);
        clientRepository.save(client);
        return clientMapper.userToUserDto(client);
    }

    @Override
    public void confirm(ClientDto clientDto) {
        clientRepository.findById(clientDto.getId()).get().setConfirmed(true);
    }
}
