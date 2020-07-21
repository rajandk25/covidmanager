package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SYMPTOM")
@Data
//Assuming that 1 Answer object is created for 1 Question.
public class SymptomAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ANSWER_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "QUESTION_ID")
    private SymptomQuestion symptomQuestion;

    @Column(name = "YES_NO")
    private Boolean yesOrNo;

    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "SYMPTOM_ANSWERS_ID")
    private SymptomAnswers symptomAnswers;
}
