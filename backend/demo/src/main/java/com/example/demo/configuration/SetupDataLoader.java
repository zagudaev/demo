package com.example.demo.configuration;

import com.example.demo.jpa.ManDao;
import com.example.demo.jpa.RoleDao;
import com.example.demo.model.Man;
import com.example.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final ManDao manDao;
    private final RoleDao roleDao;
    private boolean alreadySetup = false;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Role createRoleIfNotFound(String name) {
        Role role = roleDao.findByName(name).orElse(null);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleDao.save(role);
        }
        return role;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) return;
        createRoleIfNotFound("user");
        Role roleAdmin = createRoleIfNotFound("admin");
        Man man = manDao.findByLogin("admin").orElse(null);
        if (man == null) {
            man = new Man();
            man.setLogin("admin");
            man.setPassword(bCryptPasswordEncoder.encode("admin"));
            man.setAddress("-");
            man.setBirthDate(LocalDate.now());
            man.setEmail("-");
            man.setFirstName("-");
            man.setLastName("-");
            man.setSecondName("-");
            man.setPhone("-");
            man.setRole(roleAdmin);
            manDao.save(man);
        }
        alreadySetup = true;
    }
}
