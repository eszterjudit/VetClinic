package com.me.vetclinic.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @SequenceGenerator(name="address_generator", sequenceName="address_sequence", initialValue = 12)
    @GeneratedValue(generator = "address_generator")
    private Long id;

    @Column private String country;
    @Column private String city;
    @Column private String street;
    @Column private String zip;

}
