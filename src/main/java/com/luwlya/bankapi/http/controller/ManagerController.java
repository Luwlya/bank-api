package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.CreateManagerRequest;
import com.luwlya.bankapi.dto.ManagerDto;
import com.luwlya.bankapi.dto.ManagerListDto;
import com.luwlya.bankapi.dto.UpdateManagerRequest;
import com.luwlya.bankapi.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
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