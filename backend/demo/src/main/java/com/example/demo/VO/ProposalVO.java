package com.example.demo.VO;

import com.example.demo.jpa.StructureProposalDao;
import com.example.demo.model.ProductStatusEnum;
import com.example.demo.model.Proposal;
import com.example.demo.model.StructureProposal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProposalVO {
    private Long id;
    private String customer;
    private String contact;
    private String address;
    private String comment;
    private ProductStatusEnum status;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime createdWhen;
    private List<ProductVO> products;

    public ProposalVO(Proposal proposal, StructureProposalDao structureProposalDao) {
        this.id = proposal.getId();
        this.customer = proposal.getCustomer();
        this.contact = proposal.getContact();
        this.address = proposal.getAddress();
        this.comment = proposal.getComment();
        this.status = proposal.getStatus();
        this.createdWhen = proposal.getCreatedWhen();
        List<StructureProposal> structureProposals = structureProposalDao.findAllByProposal_Id(proposal.getId());
        if (!structureProposals.isEmpty())
            this.products = structureProposals
                    .stream()
                    .map(structureProposal -> new ProductVO(structureProposal.getProduct(),
                            structureProposal.getQuantity(),structureProposal.getPrice())).collect(Collectors.toList());
    }
}
