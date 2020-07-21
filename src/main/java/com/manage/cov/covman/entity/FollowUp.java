package com.manage.cov.covman.entity;

import com.manage.cov.covman.utils.FollowUpStateEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FOLLOW_UP")
@Data
public class FollowUp {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "FOLLOW_UP_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @OneToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private FollowUpStateEnum followUpState;

    @Embedded
    private Modification modification;
}
