package com.example.demo.VO;

import com.example.demo.model.Man;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ManVO extends ModelVO {
    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private String role;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
    // TODO:  Добавить изображение

    public ManVO(Man man) {
        super(man.getId());
        this.firstName = man.getFirstName();
        this.secondName = man.getSecondName();
        this.lastName = man.getLastName();
        this.login = man.getLogin();
        this.email = man.getEmail();
        this.phone = man.getPhone();
        this.birthDate = man.getBirthDate();
        this.role = man.getRole().getName();
    }
}
