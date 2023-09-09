package com.luwlya.bankapi.http;

import com.luwlya.bankapi.model.Manager;
import com.luwlya.bankapi.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ManagerUserDetailsService implements UserDetailsService {
    private ManagerRepository managerRepository;

    @Autowired
    public ManagerUserDetailsService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Manager manager = managerRepository.getByEmail(email);
        if (manager == null) {
            throw new UsernameNotFoundException("No manager is found with email: " + email);
        }
        return User.withUsername(email).password(manager.passwordHash()).roles("ADMIN").build();
    }
}
