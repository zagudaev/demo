package com.example.demo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ModelVO {
    private Long id;

    public ModelVO(Long id){
        this.id = id;
    }
}
