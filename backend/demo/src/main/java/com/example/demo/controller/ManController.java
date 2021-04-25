package com.example.demo.controller;

import com.example.demo.VO.ManVO;
import com.example.demo.form.ManForm;
import com.example.demo.model.Man;
import com.example.demo.service.ManService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class ManController {
    private final ManService manService;

    @GetMapping("")
    public ManVO getManFromAuth(@AuthenticationPrincipal Man man){
        return manService.getManFromAuth(man);
    }

    @PostMapping("")
    public Long saveUser(@Valid @RequestBody ManForm manForm) {
        return manService.saveUser(manForm);
    }

    @PutMapping("")
    public Long update(@Valid @RequestBody ManForm manForm){
        return manService.update(manForm);
    }

    @GetMapping("/all")
    public List<ManVO> findAll(){
        return manService.findAll();
    }

    @GetMapping("/{id}")
    public ManVO findById(@PathVariable Long id){
        return manService.findById(id);
    }

    @GetMapping("/search")
    public List<ManVO> search(
            @RequestParam(value = "firstName",required = false) String firstName,
            @RequestParam(value = "secondName",required = false) String secondName,
            @RequestParam(value = "lastName",required = false) String lastName,
            @RequestParam(value = "phone",required = false) String phone,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "login",required = false) String login
            ){
        return manService.search(firstName,secondName,lastName,phone,email,login);
    }
}
