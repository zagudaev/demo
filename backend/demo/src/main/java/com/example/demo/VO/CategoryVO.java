package com.example.demo.VO;

import com.example.demo.jpa.CategoryDao;
import com.example.demo.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryVO extends ModelVO {
    private String name;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate deprecatedFrom;
    private List<CategoryVO> children;
    private PictureVO picture;

    public CategoryVO(Category category, CategoryDao categoryDao) {// TODO: НЕ РАБОТАЕТ , РЕКУРСИЯ
        super(category.getId());
        this.name = category.getName();
        this.description = category.getDescription();
        this.deprecatedFrom = category.getDeprecatedFrom();
        this.children = categoryDao.findAllByParent_Id(category.getId())
                .stream()
                .map(c -> new CategoryVO(c, categoryDao))
                .collect(Collectors.toList());
        if (category.getPicture() != null)
            this.picture = new PictureVO(category.getPicture());
    }
}
