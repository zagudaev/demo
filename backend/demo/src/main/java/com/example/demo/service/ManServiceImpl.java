package com.example.demo.service;

import com.example.demo.VO.ManVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.form.ManForm;
import com.example.demo.jpa.ManDao;
import com.example.demo.jpa.RoleDao;
import com.example.demo.model.Man;
import com.example.demo.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManServiceImpl implements ManService {
    private final RoleDao roleDao;
    private final ManDao manDao;

    @Override
    @Transactional
    public Long update(ManForm manForm) {
        Man man = manDao.findById(manForm.getId())
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Не найден пользователь с ID = " + manForm.getId()));
        man = manForm.update(man);
        return manDao.save(man).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Man findByLogin(String login) {
        return manDao.findByLogin(login)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Man NOT FOUND"));
    }

    @Override
    @Transactional(readOnly = true)
    public ManVO findById(Long id) {
        return new ManVO(manDao.findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Не найден пользователь с ID = " + id)));
    }

    @Override
    @Transactional
    public Long saveUser(ManForm manForm) {
        if (manDao.findByLogin(manForm.getLogin()).orElse(null) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите другое имя пользователя, данный login уже занят : " + manForm.getLogin());
        Man man = manForm.toMan();
        Role role = roleDao.findByName("user")
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Role NOT 'USER' FOUND"));
        man.setRole(role);
        man.setPassword(bCryptPasswordEncoder().encode(man.getPassword()));
        return manDao.save(man).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManVO> findAll() {
        return manDao.findAll()
                .stream()
                .map(ManVO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Man man = manDao.findByLogin(s)
                .orElse(null);
        if (man == null) return new User(" ", " ", true, true, true, true,
                getGrantedAuthorities("user"));
        return new User(man.getLogin(), man.getPassword(), true, true, true, true,
                getGrantedAuthorities(man.getRole().getName()));
    }

    private List<? extends GrantedAuthority> getGrantedAuthorities(String privilege) {
        return Arrays.asList(new SimpleGrantedAuthority(privilege));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManVO> search(String firstName, String secondName, String lastName, String phone, String email, String login) {
        if (firstName == null && secondName == null && lastName == null && phone == null && email == null && login == null)
            return Collections.emptyList();
        return manDao.search(firstName, secondName, lastName, phone, email, login)
                .stream()
                .map(ManVO::new)
                .collect(Collectors.toList());
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional(readOnly = true)
    public ManVO getManFromAuth(Man man) {
        return new ManVO(findByLogin(man.getLogin()));
    }
}
