package com.example.demo.form;

import com.example.demo.VO.ProposalVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.jpa.ProductDao;
import com.example.demo.jpa.ProposalDao;
import com.example.demo.jpa.StructureProposalDao;
import com.example.demo.model.*;
import com.example.demo.utils.Helper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProposalForm {
    private Long id;
    @NotBlank
    private String customer;
    @NotBlank
    private String contact;
    @NotEmpty
    private List<ProductFormForProposal> productIds;
    @NotBlank
    private String address;
    @NotBlank
    private String comment;

    @Data
    public static class ProductFormForProposal{
        private Long id;
        @NotNull
        private Integer quantity;
    }

    public Proposal toProposal(ProductDao productDao, Man man, StructureProposalDao structureProposalDao){
        if (productIds.size() == 0)
            throw new ResponseException(HttpStatus.BAD_REQUEST,"В заявке нет товаров!");
        Proposal proposal = new Proposal();
        Helper.auditSave(proposal);
        proposal.setMan(man);
        proposal.setAddress(address);
        proposal.setContact(contact);
        proposal.setCustomer(customer);
        proposal.setComment(comment);
        proposal.setStatus(ProductStatusEnum.CREATED);
        List<StructureProposal> structureProposals = toStructureProposal(productDao,proposal);
        structureProposalDao.saveAll(structureProposals);
        proposal.setStructureProposal(structureProposals);
        return proposal;
    }

    public List<StructureProposal> toStructureProposal(ProductDao productDao,Proposal proposal){
        List<StructureProposal> structureProposals = productIds
                .stream()
                .map(p->{
                    Product product = productDao.findById(p.getId())
                            .orElseThrow(()-> new ResponseException(HttpStatus.BAD_REQUEST,"Товар не нейден! ID = "+ p.getId()));
                    product.setQuantity(product.getQuantity() - p.quantity);
                    if (product.getQuantity() < 0)
                        throw new ResponseException(HttpStatus.BAD_REQUEST,"Товар отсутсвует! Не видишь что ли...");
                    StructureProposal structureProposal = new StructureProposal();
                    structureProposal.setProduct(product);
                    structureProposal.setPrice(product.getPrice());
                    structureProposal.setQuantity(p.getQuantity());
                    structureProposal.setProposal(proposal);
                    return structureProposal;
                }).collect(Collectors.toList());
        return structureProposals;
    }
}
