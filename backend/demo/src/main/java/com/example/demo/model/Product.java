package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Product extends AuditEntity{

    private String name;
    @Column(length = 2048)
    private String description;
    private Float price;
    private Integer quantity;
    private String article;

    @Column(name = "deprecated_from")
    private LocalDate deprecatedFrom;

    @Enumerated(EnumType.STRING)
    private PlaceInBoxEnum place;

    @OneToMany(mappedBy = "product")
    private List<StructureProposal> structureProposals;

    @OneToMany(mappedBy = "product")
    private List<CompositionBox> compositionBoxes;

    @OneToMany(mappedBy = "product")
    private List<Feedback> feedBacks;

    @OneToMany(mappedBy = "product")
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn
    private Category category;
}
