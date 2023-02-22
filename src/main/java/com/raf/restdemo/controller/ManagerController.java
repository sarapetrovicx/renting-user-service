package com.raf.restdemo.controller;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.security.CheckSecurity;
import com.raf.restdemo.service.ManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService managerService;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String activation;

    public ManagerController(ManagerService managerService, JmsTemplate jmsTemplate, MessageHelper messageHelper,
                             @Value("activation_destination") String activation) {
        this.managerService = managerService;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.activation = activation;
    }

    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<ManagerDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                        Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ManagerDto> saveUser(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/confirmRegistration/{activationCode}")
    public void confirm(@RequestBody @Valid ManagerDto managerDto){
        managerService.confirm(managerDto);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<ManagerDto> editManager(@PathVariable("id") Long id, @RequestBody ManagerCreateDto managerCreateDto){
        return new ResponseEntity<>(managerService.edit(id, managerCreateDto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping("/deactive/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ManagerDto> deactiveManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id){
        return new ResponseEntity<> (managerService.changeActivity(id, false), HttpStatus.CREATED);
    }

    @PutMapping("/activate/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<ManagerDto> activateManager(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id) {
        return new ResponseEntity<> (managerService.changeActivity(id, true), HttpStatus.CREATED);
    }
}
