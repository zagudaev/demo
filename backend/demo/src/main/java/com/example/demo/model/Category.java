package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Category extends AuditEntity{

    private String name;
    private String description;

    @Column(name = "deprecated_from")
    private LocalDate deprecatedFrom;

    @ManyToOne
    @JoinColumn(
            name = "category_parent_id",
            foreignKey = @ForeignKey(
                    name = "category_2_parent"
            )
    )
    private Category parent;

    @OneToMany(mappedBy = "category")
    private List<Box> boxes;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @OneToOne(mappedBy = "category")
    @JoinColumn
    private Picture picture;
}
