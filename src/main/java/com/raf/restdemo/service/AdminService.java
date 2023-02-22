package com.raf.restdemo.service;


import com.raf.restdemo.dto.AdminDto;
import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<AdminDto> findAll(Pageable pageable);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
