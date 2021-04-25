package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "feed_back")
public class Feedback extends AuditEntity {
    private Float rating;
    private String comment;

    @ManyToOne
    @JoinColumn
    private Man man;

    @ManyToOne
    @JoinColumn
    private Score score;

    @ManyToOne
    @JoinColumn
    private Product product;

    @OneToMany(mappedBy = "feedBack")
    List<Picture> pictures;
}
