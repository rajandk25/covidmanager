package com.manage.cov.covman.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

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

    @ManyToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnoreProperties("employee")
    private List<Student> students = new ArrayList<>();

    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    @Embedded
    private Modification modification;

}
