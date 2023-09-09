package com.luwlya.bankapi.service;

import com.luwlya.bankapi.dto.manager.CreateManagerRequest;
import com.luwlya.bankapi.dto.manager.ManagerDto;
import com.luwlya.bankapi.dto.manager.ManagerListDto;
import com.luwlya.bankapi.dto.manager.UpdateManagerRequest;

import java.util.UUID;

public interface ManagerService {
    ManagerDto createManager(CreateManagerRequest request);

    ManagerDto getManager(UUID id);

    ManagerListDto getAllManagers();

    ManagerDto updateManager(UUID id, UpdateManagerRequest update);

    void deleteManager(UUID id);
}
