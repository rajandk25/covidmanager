package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SCHOOL_DISTRICT")
@Data
public class SchoolDistrict {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "DISTRICT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Address address;
/*
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<School> schools;*/
}
