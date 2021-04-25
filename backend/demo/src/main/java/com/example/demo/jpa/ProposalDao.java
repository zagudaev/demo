package com.example.demo.jpa;

import com.example.demo.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalDao extends JpaRepository<Proposal, Long> {
}
