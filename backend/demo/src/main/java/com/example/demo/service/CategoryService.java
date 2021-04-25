package com.example.demo.service;

import com.example.demo.VO.CategoryByIdVO;
import com.example.demo.VO.CategoryVO;
import com.example.demo.form.CategoryForm;

import java.util.List;

public interface CategoryService {
    Long save(CategoryForm categoryForm);

    Long update(CategoryForm categoryForm);

    Long deprecated(Long id);

    CategoryByIdVO findById(Long id);

    CategoryVO findByIdAndChildren(Long id);

    List<CategoryVO> fullTreeCategory();

}
