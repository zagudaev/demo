package com.example.demo.service;

import com.example.demo.VO.ManVO;
import com.example.demo.form.ManForm;
import com.example.demo.model.Man;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface ManService extends UserDetailsService {
    Man findByLogin(String login);

    ManVO findById(Long id);

    Long saveUser(ManForm manForm);

    Long update(ManForm manForm);

    List<ManVO> findAll();

    List<ManVO> search(String firstName, String secondName, String lastName, String phone, String email, String login);

    ManVO getManFromAuth(Man man);
}
