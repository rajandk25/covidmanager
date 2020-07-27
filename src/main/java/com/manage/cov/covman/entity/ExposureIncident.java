package com.manage.cov.covman.entity;

import com.manage.cov.covman.utils.ExposureStateEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "EXPOSURE_INCIDENT")
@Data
public class ExposureIncident {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "EXPOSURE_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private ExposureStateEnum exposureState;

    @Column(name = "COMMENTS")
    private String comments;

    @Embedded
    private Modification modification;
}
