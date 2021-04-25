package com.example.demo.controller;

import com.example.demo.VO.ProposalVO;
import com.example.demo.form.ProposalForm;
import com.example.demo.model.Man;
import com.example.demo.service.ProposalService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private final ProposalService proposalService;



    @PostMapping("")
    public Long save(@Valid @RequestBody ProposalForm proposalForm, @AuthenticationPrincipal Man man) {
        return proposalService.save(proposalForm, man);
    }

    @GetMapping("/{id}")
    public ProposalVO findById(@PathVariable Long id){
        return proposalService.findById(id);
    }

    @GetMapping("")
    @ApiResponse(description = "НЕ РЕАЛИЗОВАНО")
    public List<ProposalVO> findAll(
            @RequestParam(value = "status",required = false) String status,
            @RequestParam(value = "sort",required = false) String sort
    ){
        return null;// TODO:  ПОИСК С ФИЛЬТРАМИ
    }


}
