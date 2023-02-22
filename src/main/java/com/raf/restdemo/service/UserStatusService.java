package com.raf.restdemo.service;

import com.raf.restdemo.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserStatusService {

    UserStatusDto add(UserStatusDto userStatusDto);

    Page<UserStatusDto> findAll(Pageable pageable);

    UserStatusDto edit(Long id, UserStatusDto userStatusDto);


}
