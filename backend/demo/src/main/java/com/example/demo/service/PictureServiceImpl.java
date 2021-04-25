package com.example.demo.service;

import com.example.demo.VO.PictureVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.jpa.PictureDao;
import com.example.demo.model.Picture;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureDao pictureDao;


    @Override
    public Picture findById(Long id) {
        return pictureDao.findById(id)
                .orElseThrow(()-> new ResponseException(HttpStatus.BAD_REQUEST,"Изображение не найдено, ID = " + id));
    }
}
