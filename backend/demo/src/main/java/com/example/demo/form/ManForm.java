package com.example.demo.form;

import com.example.demo.model.Man;
import com.example.demo.utils.Helper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ManForm {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    public Man toMan(){
        Man man = new Man();
        man = update(man);
        Helper.auditSave(man);
        return man;
    }

    public Man update(Man man){
        man.setFirstName(firstName);
        man.setPassword(password);
        man.setSecondName(secondName);
        man.setLastName(lastName);
        man.setLogin(login);
        man.setPassword(password);
        man.setEmail(email);
        man.setPhone(phone);
        man.setBirthDate(birthDate);
        man.setAddress(address);
        Helper.auditUpdate(man);
        return man;
    }
}
