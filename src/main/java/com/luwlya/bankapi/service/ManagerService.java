package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.CreateManagerRequest;
import com.luwlya.bankapi.dto.ManagerDto;
import com.luwlya.bankapi.dto.ManagerListDto;
import com.luwlya.bankapi.dto.UpdateManagerRequest;

import java.util.UUID;

public interface ManagerService {
    ManagerDto createManager(CreateManagerRequest request);

    ManagerDto getManager(UUID id);

    ManagerListDto getAllManagers();

    ManagerDto updateManager(UUID id, UpdateManagerRequest update);

    void deleteManager(UUID id);
}
