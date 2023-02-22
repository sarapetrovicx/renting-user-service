package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<ClientDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> saveUser(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/confirmRegistration/{activationCode}")
    public void confirm(@RequestBody @Valid ClientDto clientDto){
        clientService.confirm(clientDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClientDto> editClient(@PathVariable("id") Long id, @RequestBody ClientCreateDto clientCreateDto){
        return new ResponseEntity<>(clientService.edit(id, clientCreateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.findDiscount(id), HttpStatus.OK);
    }

    @PutMapping("/deactive/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ClientDto> deactiveManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id){
        return new ResponseEntity<> (clientService.changeActivity(id, false), HttpStatus.CREATED);
    }

    @PutMapping("/activate/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ClientDto> activateManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id) {
        return new ResponseEntity<> (clientService.changeActivity(id, true), HttpStatus.CREATED);
    }

}
