package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);

    ClientDto findById(Long id);

    ClientDto add(ClientCreateDto clientCreateDto);

    ClientDto changeRentDays(ClientRentDaysDto clientRentDaysDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ClientDto edit(Long id, ClientCreateDto clientCreateDto);

    DiscountDto findDiscount(Long id);

    ClientDto changeActivity(Long id, boolean activity);

    void confirm(ClientDto clientDto);
}
