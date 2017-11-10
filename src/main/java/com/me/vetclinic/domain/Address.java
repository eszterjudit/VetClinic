package com.me.vetclinic.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column private String country;
    @Column private String city;
    @Column private String street;
    @Column private String zip;

}
