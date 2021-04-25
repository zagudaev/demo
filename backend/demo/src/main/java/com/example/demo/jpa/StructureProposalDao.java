package com.example.demo.jpa;

import com.example.demo.model.StructureProposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StructureProposalDao extends JpaRepository<StructureProposal, Long> {
    List<StructureProposal> findAllByProposal_Id(Long proposalId);
}
