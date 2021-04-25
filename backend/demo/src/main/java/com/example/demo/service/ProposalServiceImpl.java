package com.example.demo.service;

import com.example.demo.VO.ProposalVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.form.ProposalForm;
import com.example.demo.jpa.ManDao;
import com.example.demo.jpa.ProductDao;
import com.example.demo.jpa.ProposalDao;
import com.example.demo.jpa.StructureProposalDao;
import com.example.demo.model.Man;
import com.example.demo.model.Proposal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.utils.Helper.proposalToMessageForCreate;

@AllArgsConstructor
@Service
public class ProposalServiceImpl implements ProposalService {
    private final ProposalDao proposalDao;
    private final StructureProposalDao structureProposalDao;
    private final ProductDao productDao;
    private final ManDao manDao;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String mailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("diplom.demo@yandex.ru");
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    @Transactional
    public Long save(ProposalForm proposalForm, Man manAuth) {
        Man man = null;
        if (manAuth != null && manAuth.getLogin() != null)
            man = manDao.findByLogin(manAuth.getLogin()).orElse(null);
        Proposal proposal = proposalForm.toProposal(productDao, man, structureProposalDao);
        proposalDao.save(proposal);
        ProposalVO proposalVO = new ProposalVO(proposal, structureProposalDao);
        sendMail("Kjolenskap@yandex.ru", "Новая заявка!", proposalToMessageForCreate(proposalVO));
        return proposal.getId();
    }

    @Override
    public ProposalVO findById(Long id) {
        return new ProposalVO(proposalDao.findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Заказ не найден! ID =" + id)),
                structureProposalDao);
    }
}
