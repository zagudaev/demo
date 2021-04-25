package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class AuditEntity extends ModelEntity {
    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "created_when")
    private LocalDateTime createdWhen;

    @Column(name = "update_by")
    private String updatedBy;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "update_when")
    private LocalDateTime updatedWhen;
}
