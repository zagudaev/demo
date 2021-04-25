package com.example.demo.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PictureForm {
    private Long id;
    private String name;
    private String type;
    private String contentType;
    private LocalDate deprecatedFrom;
    @NotBlank
    private String content;
}
