package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SCHOOL")
@Data
public class School {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "SCHOOL_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "DISTRICT_ID")
    private SchoolDistrict district;

}
