package com.example.demo.form;

import com.example.demo.exceptions.ResponseException;
import com.example.demo.jpa.CategoryDao;
import com.example.demo.jpa.PictureDao;
import com.example.demo.model.Category;
import com.example.demo.model.Picture;
import com.example.demo.utils.Helper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class CategoryForm {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate deprecatedFrom;
    private Long parentId;
    private PictureForm picture;

    public Category toCategory(CategoryDao categoryDao, PictureDao pictureDao) {
        Category category = new Category();
        category.setName(name);
            category.setDeprecatedFrom(deprecatedFrom);
        category.setDescription(description);
        if (parentId != null) {
            Category parent = categoryDao.findById(parentId)
                    .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Нет категории с id = " + parentId));
            category.setParent(parent);
        }
        Helper.auditSave(category);
        if (picture != null)
            if (picture.getContent() != null) {
                Picture picture = new Picture();
                Helper.base64ToPicture(this.picture.getContent(), picture);
                Helper.auditSave(picture);
                picture.setCategory(category);
                pictureDao.save(picture);
                category.setPicture(picture);
            }
        return category;
    }

    public Category update(CategoryDao categoryDao, PictureDao pictureDao) {
        Category category = categoryDao.findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Нет категории с id = " + parentId));
        category.setDescription(description);
        category.setName(name);
        category.setDeprecatedFrom(deprecatedFrom);
        Helper.auditUpdate(category);
        if (picture != null)
            if (this.picture.getContent() != null) {
                if (category.getPicture() != null) {
                    Picture picture = category.getPicture();
                    Helper.base64ToPicture(this.picture.getContent(), picture);
                    Helper.auditUpdate(picture);
                    pictureDao.save(picture);
                } else {
                    Picture picture = new Picture();
                    Helper.base64ToPicture(this.picture.getContent(), picture);
                    Helper.auditSave(picture);
                    picture.setCategory(category);
                    pictureDao.save(picture);
                    category.setPicture(picture);
                }
            }
        return category;
    }
}
