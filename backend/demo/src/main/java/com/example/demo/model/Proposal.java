package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Proposal extends AuditEntity {

    private String customer;
    private String contact;
    private String address;
    private String comment;
    @Enumerated
    private ProductStatusEnum status;

    @OneToMany(mappedBy = "proposal")
    private List<StructureProposal> structureProposal;

    @ManyToOne
    @JoinColumn
    private Man man;

    @ManyToOne
    @JoinColumn
    private CompositionBox compositionBox;
}
