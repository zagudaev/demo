package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Picture extends AuditEntity {
    private String name;
    private String type;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "deprecated_from")
    private LocalDate deprecatedFrom;

    @Lob
    private byte[] content;

    @OneToOne
    @JoinColumn
    private Man man;

    @ManyToOne
    @JoinColumn
    private TypeBox typeBox;

    @ManyToOne
    @JoinColumn
    private Feedback feedBack;

    @ManyToOne
    @JoinColumn
    private Product product;

    @OneToOne
    private Category category;
}
