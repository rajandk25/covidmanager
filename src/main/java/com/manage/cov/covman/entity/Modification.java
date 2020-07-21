package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Modification {

    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @PrePersist
    public void createDateTime() {
        this.modifiedAt = LocalDateTime.now();
    }
}
