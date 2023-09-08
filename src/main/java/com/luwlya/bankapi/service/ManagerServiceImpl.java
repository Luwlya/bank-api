package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateManagerRequest;
import com.luwlya.bankapi.dto.ManagerDto;
import com.luwlya.bankapi.dto.ManagerListDto;
import com.luwlya.bankapi.dto.UpdateManagerRequest;
import com.luwlya.bankapi.model.ManagerStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;
@Service
public class ManagerServiceImpl implements ManagerService {
    @Override
    public ManagerDto createManager(CreateManagerRequest request) {
        return new ManagerDto(UUID.randomUUID(),
                request.firstName(),
                request.lastName(),
                request.email(),
                ManagerStatus.ACTIVE,
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    @Override
    public ManagerDto getManager(UUID id) {
        return null;
    }

    @Override
    public ManagerListDto getAllManagers() {
        return null;
    }

    @Override
    public ManagerDto updateManager(UUID id, UpdateManagerRequest update) {
        return null;
    }

    @Override
    public void deleteManager(UUID id) {

    }
}
