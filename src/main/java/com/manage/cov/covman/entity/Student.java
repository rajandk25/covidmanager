package com.manage.cov.covman.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotEmpty
    private String lastName;

    @Column(name = "GRADE")
    @NotNull
    private Integer grade;

    @Column(name = "AGE")
    private Integer age;

    //one parent account per household
    @ManyToOne
    @JoinColumn(name="PARENT_ID", updatable = false)
    @JsonBackReference
    private Parent parent;

    //assigned teacher to check-in, nurse to update
    @ManyToMany()
    @JoinTable(
            name = "Employee_Student",
            joinColumns = { @JoinColumn(name = "STUDENT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "EMPLOYEE_ID") }
    )
    @ToString.Exclude
    private List<Employee> employee;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @ToString.Exclude
    private SymptomAnswers symptomAnswers;

    @ManyToOne
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    @Embedded
    private Modification modification;
}
