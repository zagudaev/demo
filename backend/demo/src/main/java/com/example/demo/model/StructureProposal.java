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
@Entity(name = "structure_proposal")
public class StructureProposal extends ModelEntity{

    @ManyToOne
    @JoinColumn
    private Proposal proposal;

    @ManyToOne
    @JoinColumn
    private Product product;

    private Integer quantity;

    private Float price;
}
