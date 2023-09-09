package com.luwlya.bankapi.repository;

import com.luwlya.bankapi.model.Manager;

import java.util.List;
import java.util.UUID;

public interface ManagerRepository {
    void insert(Manager manager);

    Manager get(UUID id);

    List<Manager> getAll();

    void update(Manager manager);

    boolean delete(UUID id);

    Manager getByEmail(String email);
}
