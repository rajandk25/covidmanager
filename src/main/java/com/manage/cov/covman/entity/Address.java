package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//This means that the fields in this class are included in another Entity.
//This class doesn't map to a table in the DB. There is not Address table.
//If User embed this class, then the User table has all these columns.
@Embeddable
@Data
public class Address {

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "PHONE")
    private String phone;
}
