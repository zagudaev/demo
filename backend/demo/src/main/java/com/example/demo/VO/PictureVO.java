package com.example.demo.VO;

import com.example.demo.model.Picture;
import lombok.Data;

@Data
public class PictureVO {
    private Long id;
    private byte[] content;
    private Integer size;
    private String contentType;

    public PictureVO(Picture picture){
        this.id = picture.getId();
//        this.content = picture.getContent();
        this.contentType = picture.getContentType();
        this.size = picture.getContent().length;
    }
}
