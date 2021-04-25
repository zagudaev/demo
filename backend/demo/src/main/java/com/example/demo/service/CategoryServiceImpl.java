package com.example.demo.service;

import com.example.demo.VO.CategoryByIdVO;
import com.example.demo.VO.CategoryVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.form.CategoryForm;
import com.example.demo.jpa.CategoryDao;
import com.example.demo.jpa.PictureDao;
import com.example.demo.model.Category;
import com.example.demo.utils.Helper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;
    private final PictureDao pictureDao;

    @Override
    @Transactional
    public Long save(CategoryForm categoryForm) {
        Category category = categoryForm.toCategory(categoryDao, pictureDao);
        return categoryDao.save(category).getId();
    }

    @Override
    @Transactional
    public Long update(CategoryForm categoryForm) {
        return categoryDao.save(categoryForm.update(categoryDao, pictureDao)).getId();
    }

    @Override
    public CategoryByIdVO findById(Long id) {
        return new CategoryByIdVO(categoryDao.findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Нет категории с id = " + id)));
    }

    @Override
    public CategoryVO findByIdAndChildren(Long id) {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Нет категории с id = " + id));
        return new CategoryVO(category, categoryDao);
    }

    @Override
    @Transactional
    public List<CategoryVO> fullTreeCategory() {
        return categoryDao.findAllByParentIdIsNull()
                .stream()
                .map(c -> new CategoryVO(c, categoryDao))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long deprecated(Long id) {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Нет категории с id = " + id));
        Helper.auditUpdate(category);
        category.setDeprecatedFrom(LocalDate.now());
        return categoryDao.save(category).getId();
    }
}
