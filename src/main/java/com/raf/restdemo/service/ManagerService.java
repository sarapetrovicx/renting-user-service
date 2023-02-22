package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {

    ManagerDto add(ManagerCreateDto managerCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    Page<ManagerDto> findAll(Pageable pageable);

    ManagerDto edit(Long id, ManagerCreateDto managerCreateDto);

    ManagerDto changeActivity(Long id, boolean activity);

    void confirm(ManagerDto managerDto);

}