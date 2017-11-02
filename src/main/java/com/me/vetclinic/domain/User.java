package com.me.vetclinic.domain;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class User {

    @Column private String firstName;
    @Column private String lastName;
    @Column private String email;
    @Column private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
