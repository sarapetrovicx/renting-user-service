package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Manager;
import com.raf.restdemo.dto.ManagerCreateDto;
import com.raf.restdemo.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto){
        Manager manager = new Manager();
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setBirthDate(managerCreateDto.getBirthDate());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setCompanyId(managerCreateDto.getCompanyId());
        return manager;
    }

    public ManagerDto managerToManagerDto(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setUsername(manager.getUsername());
        managerDto.setEmail(manager.getEmail());
        managerDto.setBirthDate(manager.getBirthDate());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setCompanyId(manager.getCompanyId());
        managerDto.setStartedWorking(manager.getStartedWorking());
        managerDto.setActive(manager.isActive());
        managerDto.setActivationCode(manager.getActivationCode());
        return managerDto;
    }
}
