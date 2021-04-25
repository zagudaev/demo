package com.example.demo.form;

import com.example.demo.jpa.CategoryDao;
import com.example.demo.jpa.PictureDao;
import com.example.demo.model.Category;
import com.example.demo.model.Picture;
import com.example.demo.model.PlaceInBoxEnum;
import com.example.demo.model.Product;
import com.example.demo.utils.Helper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductForm {
    private Long id;
    @NotNull
    private Long categoryId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Float price;
    @NotNull
    private Integer quantity;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate deprecatedFrom;
    private PlaceInBoxEnum place;
    private List<PictureForm> pictures;
    @NotBlank
    private String article;

    public Product toProduct(CategoryDao categoryDao, PictureDao pictureDao) {
        Product product = new Product();
        product = update(product, categoryDao, pictureDao);
        Helper.auditSave(product);
        return product;
    }

    public Product update(Product product, CategoryDao categoryDao, PictureDao pictureDao) {
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDeprecatedFrom(deprecatedFrom);
        product.setPlace(place);
        product.setArticle(article);
        Category category = categoryDao.findById(categoryId)
                .orElse(null);
        product.setCategory(category);
        Helper.auditUpdate(product);

        if (product.getId() != null) {
            List<Long> listIdNoForRemove = this.pictures.stream()
                    .filter(pictureForm -> pictureForm.getId() != null)
                    .map(PictureForm::getId).collect(Collectors.toList());
            List<Picture> picturesForRemove = pictureDao.findAllByProduct_IdAndNotListId(product.getId(),listIdNoForRemove);
            if (picturesForRemove.size() > 0)
                pictureDao.deleteAll(picturesForRemove);
        }
        List<Picture> pictures = this.pictures
                .stream()
                .filter(pictureForm -> pictureForm.getId() == null)
                .map(p -> {
            Picture picture = new Picture();
            Helper.base64ToPicture(p.getContent(),picture);
            picture.setProduct(product);
            Helper.auditSave(picture);
            pictureDao.save(picture);
            return picture;
        }).collect(Collectors.toList());

        product.setPictures(pictures);

        return product;
    }
}
