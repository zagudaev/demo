package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
public class Role extends ModelEntity{

    private String name;
    @OneToMany(mappedBy = "role")
    private Collection<Man> users;
}