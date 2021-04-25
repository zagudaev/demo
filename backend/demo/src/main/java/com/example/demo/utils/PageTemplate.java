package com.example.demo.utils;

import com.example.demo.utils.validation.DirectionValid;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PageTemplate {
    @NotBlank
    private String sortField;
    @DirectionValid
    private String direction;
    @NotNull
    private Integer size;
    @NotNull
    private Integer page;

    public Pageable toPage(){
        return PageRequest.of(page,size, Sort.Direction.valueOf(direction.toUpperCase()),sortField);
    }
}
