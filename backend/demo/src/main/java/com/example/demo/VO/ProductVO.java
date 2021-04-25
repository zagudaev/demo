package com.example.demo.VO;

import com.example.demo.model.PlaceInBoxEnum;
import com.example.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductVO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private Integer quantityForProposal;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate deprecatedFrom;
    private PlaceInBoxEnum place;
    private CategoryByIdVO category;
    private String article;
    private List<PictureVO> pictures;

    public ProductVO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.deprecatedFrom = product.getDeprecatedFrom();
        this.place = product.getPlace();
        this.article = product.getArticle();
        if (product.getCategory() != null)
            this.category = new CategoryByIdVO(product.getCategory());
        if (product.getPictures().size() > 0)
            this.pictures = product.getPictures()
                    .stream()
                    .map(PictureVO::new)
                    .collect(Collectors.toList());
    }

    public ProductVO(Product product, Integer quantityForProposal, Float priceFromProposal) {
        this(product);
        this.quantityForProposal = quantityForProposal;
    }
}
