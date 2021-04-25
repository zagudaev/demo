package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "composition_box")
public class CompositionBox extends AuditEntity {

    @ManyToOne
    @JoinColumn
    private Box box;

    @ManyToOne
    @JoinColumn
    private Product product;
}
