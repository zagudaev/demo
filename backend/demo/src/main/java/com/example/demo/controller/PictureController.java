package com.example.demo.controller;

import com.example.demo.VO.PictureVO;
import com.example.demo.model.Picture;
import com.example.demo.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Picture picture = pictureService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(picture.getContentType()))
                .body(picture.getContent());
    }
}
