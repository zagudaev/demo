package com.example.demo.jpa;

import com.example.demo.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureDao extends JpaRepository<Picture, Long> {

    @Query("select p from Picture p where p.product.id = ?1 and p.id not in ?2")
    List<Picture> findAllByProduct_IdAndNotListId(Long productId, List<Long> listId);
}
