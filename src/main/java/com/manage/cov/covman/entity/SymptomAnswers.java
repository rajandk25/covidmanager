package com.manage.cov.covman.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.manage.cov.covman.utils.CheckerTypeEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SYMPTOM_ANSWER")
@Data
//This class is basically 1 daily check-in
//1 check-in must have all the symptoms
public class SymptomAnswers implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "SYMPTOM_ANSWERS_ID")
    private Long id;

    //this means that use the property symptomAnswer in Symptom entity to map relationship
    @OneToMany(mappedBy = "symptomAnswers", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<SymptomAnswer> symptomAnswer;

    @OneToOne
    @JoinColumn(name="STUDENT_ID")
    @JsonBackReference
    private Student student;

    //who is checking-in the student
    @Enumerated(EnumType.STRING)
    private CheckerTypeEnum checker;

    @Embedded
    private Modification modification;

}
