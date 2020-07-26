package com.manage.cov.covman.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARENT")
@Data
public class Parent {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "PARENT_ID")
    private Long id;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Embedded
    private Modification modification;
}
