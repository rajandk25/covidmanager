package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "SYMPTOM_QUESTION")
@Data
public class SymptomQuestion {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="QUESTION_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    @NotEmpty
    private String description;
}
