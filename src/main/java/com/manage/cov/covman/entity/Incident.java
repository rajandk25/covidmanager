package com.manage.cov.covman.entity;

import com.manage.cov.covman.utils.IncidentStateEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "INCIDENT")
@Data
public class Incident {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "INCIDENT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "SYMPTOM_ANSWERS_ID")
    private SymptomAnswers symptomAnswers;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private IncidentStateEnum incidentState;

    @Column(name = "COMMENTS")
    private String comments;

    @Embedded
    private Modification modification;
}
