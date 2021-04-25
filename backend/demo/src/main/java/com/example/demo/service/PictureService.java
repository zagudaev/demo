package com.example.demo.service;

import com.example.demo.VO.PictureVO;
import com.example.demo.model.Picture;

public interface PictureService {

    Picture findById(Long id);
}
