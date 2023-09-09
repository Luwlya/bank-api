package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.manager.CreateManagerRequest;
import com.luwlya.bankapi.dto.manager.ManagerDto;
import com.luwlya.bankapi.dto.manager.ManagerListDto;
import com.luwlya.bankapi.dto.manager.UpdateManagerRequest;
import com.luwlya.bankapi.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@Secured("ROLE_ADMIN")
public class ManagerController {
    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerDto> createManager(@RequestBody CreateManagerRequest request) {
        ManagerDto manager = managerService.createManager(request);
        return ResponseEntity.ok(manager);
    }

    @GetMapping("/managers")
    public ResponseEntity<ManagerListDto> getAllManagers() {
        return ResponseEntity.ok().body(managerService.getAllManagers());
    }

    @GetMapping("/managers/{id}")
    public ResponseEntity<ManagerDto> getManager(@PathVariable UUID id) {
        ManagerDto dto = managerService.getManager(id);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/managers/{id}")
    public ResponseEntity<ManagerDto> updateManager(@PathVariable UUID id, @RequestBody @Valid UpdateManagerRequest update) {
        ManagerDto dto = managerService.updateManager(id, update);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/managers/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable UUID id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}