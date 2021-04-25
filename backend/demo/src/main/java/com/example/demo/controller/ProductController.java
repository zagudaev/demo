package com.example.demo.controller;

import com.example.demo.VO.ProductVO;
import com.example.demo.form.ProductForm;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public Long save(@Valid @RequestBody ProductForm productForm) {
        return productService.save(productForm);
    }

    @PutMapping("")
    public Long update(@Valid @RequestBody ProductForm productForm) {
        return productService.update(productForm);
    }

    @GetMapping("/{id}")
    public ProductVO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("")
    public List<ProductVO> findAllByCategory_Id(@RequestParam("categoryId") Long categoryId){
        return productService.findAllByCategory_Id(categoryId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

}
