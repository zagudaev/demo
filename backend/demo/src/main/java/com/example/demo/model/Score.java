package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Score extends AuditEntity{
    private String score;

    @OneToMany(mappedBy = "score")
    List<Feedback> feedbackList;
}
