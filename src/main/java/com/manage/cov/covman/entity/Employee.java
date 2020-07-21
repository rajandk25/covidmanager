package com.manage.cov.covman.entity;

import com.manage.cov.covman.utils.EmployeeRoleEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee extends User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum employeeRole;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Employee_Student",
            joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "STUDENT_ID") }
    )
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    @Embedded
    private Modification modification;

}
