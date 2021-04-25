package com.example.demo.service;

import com.example.demo.VO.ProductVO;
import com.example.demo.form.ProductForm;

import java.util.List;

public interface ProductService {
    Long save(ProductForm productForm);

    Long update(ProductForm productForm);

    void delete(Long id);

    ProductVO findById(Long id);

    List<ProductVO> findAllByCategory_Id(Long categoryId);
}
