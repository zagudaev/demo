package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Box extends AuditEntity{

    @ManyToOne
    @JoinColumn
    private TypeBox typeBox;

    @ManyToOne
    @JoinColumn
    private Category category;

    @OneToMany(mappedBy = "box")
    private List<CompositionBox> compositionBoxes;
}
