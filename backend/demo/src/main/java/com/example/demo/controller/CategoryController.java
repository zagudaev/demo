package com.example.demo.controller;

import com.example.demo.VO.CategoryByIdVO;
import com.example.demo.VO.CategoryVO;
import com.example.demo.form.CategoryForm;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public Long save(@Valid @RequestBody CategoryForm categoryForm){
        return categoryService.save(categoryForm);
    }

    @GetMapping("/{id}")
    public CategoryByIdVO findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/children/{id}")
    public CategoryVO findByIdAndChildren(@PathVariable Long id){
        return categoryService.findByIdAndChildren(id);
    }

    @GetMapping("/full")
    public List<CategoryVO> fullTreeCategory(){
        return categoryService.fullTreeCategory();
    }

    @PutMapping("")
    public Long update(@Valid @RequestBody CategoryForm categoryForm){
        return categoryService.update(categoryForm);
    }

    @PutMapping("/deprecated")
    public Long deprecated(@RequestParam("id") Long id){
        return categoryService.deprecated(id);
    }

}
