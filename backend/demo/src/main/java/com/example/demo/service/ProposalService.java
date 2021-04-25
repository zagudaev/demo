package com.example.demo.service;

import com.example.demo.VO.ProposalVO;
import com.example.demo.form.ProposalForm;
import com.example.demo.model.Man;
import com.example.demo.model.Proposal;

public interface ProposalService {
    void sendMail(String mailTo, String subject, String text);

    Long save(ProposalForm proposalForm, Man man);

    ProposalVO findById(Long id);
}
