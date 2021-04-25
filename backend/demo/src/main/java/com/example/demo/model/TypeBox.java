package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class TypeBox extends AuditEntity{

    private String name;
    private Integer size;
    private String description;

    @OneToMany(mappedBy = "typeBox")
    private List<Box> boxes;

    @OneToMany(mappedBy = "typeBox")
    private List<Picture> pictures;

}
