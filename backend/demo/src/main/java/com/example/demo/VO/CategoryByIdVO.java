package com.example.demo.VO;

import com.example.demo.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CategoryByIdVO extends ModelVO{
    private String name;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate deprecatedFrom;
    private PictureVO picture;
    private CategoryByIdVO parent;

    public CategoryByIdVO(Category category){
        super(category.getId());
        this.name = category.getName();
        this.description = category.getDescription();
        this.deprecatedFrom = category.getDeprecatedFrom();
        if (category.getParent() != null)
            this.parent = new CategoryByIdVO(category.getParent());
        if (category.getPicture() !=null)
            this.picture = new PictureVO(category.getPicture());
    }
}
