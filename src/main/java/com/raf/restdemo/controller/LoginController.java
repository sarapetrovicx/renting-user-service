//package com.raf.restdemo.controller;
//
//import com.raf.restdemo.domain.Admin;
//import com.raf.restdemo.domain.Client;
//import com.raf.restdemo.domain.Manager;
//import com.raf.restdemo.dto.TokenRequestDto;
//import com.raf.restdemo.dto.TokenResponseDto;
//import com.raf.restdemo.repository.AdminRepository;
//import com.raf.restdemo.repository.ClientRepository;
//import com.raf.restdemo.repository.ManagerRepository;
//import com.raf.restdemo.service.AdminService;
//import com.raf.restdemo.service.ClientService;
//import com.raf.restdemo.service.ManagerService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/login")
//public class LoginController {
//
//    private ClientService clientService;
//    private ManagerService managerService;
//    private AdminService adminService;
//    private AdminRepository adminRepository;
//    private ClientRepository clientRepository;
//    private ManagerRepository managerRepository;
//
//    public LoginController(ClientService clientService, ManagerService managerService, AdminService adminService, AdminRepository adminRepository, ClientRepository clientRepository, ManagerRepository managerRepository) {
//        this.clientService = clientService;
//        this.managerService = managerService;
//        this.adminService = adminService;
//        this.adminRepository = adminRepository;
//        this.clientRepository = clientRepository;
//        this.managerRepository = managerRepository;
//    }
//
//    @PostMapping
//    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
//        Admin admin = null;
//        admin = adminRepository.findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
//                .orElse(null);
//        if(admin!=null){
//            return new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
//        }
//        Client client = null;
//        client = clientRepository.findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
//                .orElse(null);
//        if(client!=null){
//            return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
//        }
//        Manager manager = null;
//        manager = managerRepository.findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
//                .orElse(null);
//        if(manager!=null){
//            return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//    }
//}
