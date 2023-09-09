package com.luwlya.bankapi.service.impl;

import com.luwlya.bankapi.dto.manager.CreateManagerRequest;
import com.luwlya.bankapi.dto.manager.ManagerDto;
import com.luwlya.bankapi.dto.manager.ManagerListDto;
import com.luwlya.bankapi.dto.manager.UpdateManagerRequest;
import com.luwlya.bankapi.exception.CustomerNotFoundException;
import com.luwlya.bankapi.model.Manager;
import com.luwlya.bankapi.model.ManagerStatus;
import com.luwlya.bankapi.repository.ManagerRepository;
import com.luwlya.bankapi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {
    private ManagerRepository managerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ManagerDto createManager(CreateManagerRequest request) {
        Manager manager = new Manager(
                UUID.randomUUID(),
                ManagerStatus.ACTIVE,
                request.firstName(),
                request.lastName(),
                request.email(),
                passwordEncoder.encode(request.password()),
                OffsetDateTime.now(),
                OffsetDateTime.now());
        System.out.println(request);
        managerRepository.insert(manager);
        return dto(manager);
    }

    private ManagerDto dto(Manager manager) {
        return new ManagerDto(manager.id(),
                manager.firstName(),
                manager.lastName(),
                manager.email(),
                manager.status(),
                manager.createdAt(),
                manager.updatedAt());
    }

    @Override
    public ManagerDto getManager(UUID id) {
        Manager manager = managerRepository.get(id);
        return dto(manager);
    }

    @Override
    public ManagerListDto getAllManagers() {
        List<Manager> managers = managerRepository.getAll();
        List<ManagerDto> result = managers.stream().map(this::dto).toList();
        return new ManagerListDto(result);
    }

    @Override
    public ManagerDto updateManager(UUID id, UpdateManagerRequest update) {
        Manager manager = managerRepository.get(id);
        Manager updateManager = new Manager(id,
                manager.status(),
                update.firstName() != null ? update.firstName() : manager.firstName(),
                update.lastName() != null ? update.lastName() : manager.lastName(),
                update.email() != null ? update.email() : manager.email(),
                update.password() != null ? passwordEncoder.encode(update.password()) : manager.passwordHash(),
                manager.createdAt(),
                OffsetDateTime.now());
        managerRepository.update(updateManager);
        return dto(updateManager);
    }

    @Override
    public void deleteManager(UUID id) {
        boolean deleted = managerRepository.delete(id);
        if (deleted) {
            System.out.println("Customer " + id + " has been deleted");
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
