package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARENT")
@Data
public class Parent extends User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "PARENT_ID")
    private Long id;

    @Column(name = "EMAIL")
    @NotEmpty
    private String email;

    @Column(name = "PASSWORD")
    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @Embedded
    private Modification modification;
}
