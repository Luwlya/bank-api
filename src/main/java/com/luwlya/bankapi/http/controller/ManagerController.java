package com.luwlya.bankapi.http.controller;

import com.luwlya.bankapi.dto.CreateManagerRequest;
import com.luwlya.bankapi.dto.ManagerDto;
import com.luwlya.bankapi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerDto> createManager(@RequestBody CreateManagerRequest request){
       ManagerDto manager = managerService.createManager(request);
       return ResponseEntity.ok(manager);
    }
}
