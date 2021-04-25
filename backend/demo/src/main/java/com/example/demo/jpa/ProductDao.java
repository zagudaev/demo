package com.example.demo.jpa;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    @Query("select p " +
            " from Product p join Category c on c.id = p.category.id " +
            " where c.id = ?1 and (p.deprecatedFrom > current_date or p.deprecatedFrom is null)")
    List<Product> findAllByCategory_Id(Long categoryId);
}
