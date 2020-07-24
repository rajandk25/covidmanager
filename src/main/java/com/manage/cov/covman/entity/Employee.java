package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Employee_Student",
            joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "STUDENT_ID") }
    )
    private List<Student> students = new ArrayList<>();

    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    @Embedded
    private Modification modification;

}
