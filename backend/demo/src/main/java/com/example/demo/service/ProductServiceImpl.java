package com.example.demo.service;

import com.example.demo.VO.ProductVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.form.ProductForm;
import com.example.demo.jpa.CategoryDao;
import com.example.demo.jpa.PictureDao;
import com.example.demo.jpa.ProductDao;
import com.example.demo.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final PictureDao pictureDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Override
    @Transactional
    public Long save(ProductForm productForm) {
        return productDao.save(productForm.toProduct(categoryDao,pictureDao)).getId();
    }

    @Override
    @Transactional
    public Long update(ProductForm productForm) {
        return productDao.save(productForm
                .update(productDao.findById(productForm.getId())
                                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Товар не найден ! ID = " + productForm.getId())),
                        categoryDao,
                        pictureDao
                )).getId();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new ResponseException(HttpStatus.BAD_REQUEST, "Товар не найден ! ID = " + id));
        product.setDeprecatedFrom(LocalDate.now());
        productDao.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVO findById(Long id) {
        return new ProductVO(productDao.findById(id)
                .orElseThrow(()-> new ResponseException(HttpStatus.BAD_REQUEST,"Товар не найден ! ID = " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductVO> findAllByCategory_Id(Long categoryId) {
        return productDao.findAllByCategory_Id(categoryId)
                .stream()
                .map(product -> new ProductVO(product))
                .collect(Collectors.toList());
    }


}
