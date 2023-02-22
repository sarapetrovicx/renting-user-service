package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Client;
import com.raf.restdemo.dto.ClientCreateDto;
import com.raf.restdemo.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public ClientMapper() {

    }

    public ClientDto userToUserDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setActivationCode(client.getActivationCode());
        clientDto.setPassportNum(client.getPassportNum());
        clientDto.setPassword(client.getPassword());
        return clientDto;
    }

    public Client userCreateDtoToUser(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setPassportNum(clientCreateDto.getPassportNum());
        return client;
    }
}
