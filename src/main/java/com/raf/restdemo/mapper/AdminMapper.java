package com.raf.restdemo.mapper;
import com.raf.restdemo.domain.Admin;
import com.raf.restdemo.dto.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDto userToUserDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setEmail(admin.getEmail());
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setUsername(admin.getUsername());
        return adminDto;
    }
}
