package com.example.demo.jpa;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.parent is null and (c.deprecatedFrom > current_date or c.deprecatedFrom is null) ")
    List<Category> findAllByParentIdIsNull();

    @Query("select c from Category c where c.parent.id =?1 and (c.deprecatedFrom > current_date or c.deprecatedFrom is null) ")
    List<Category> findAllByParent_Id(Long parentId);
}
