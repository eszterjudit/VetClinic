package com.me.vetclinic.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLINIC_ID")
    private Long id;

    @Column
    private String clinicName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column
    private String openingHour;

    @Column
    private String closingHour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "CLINIC_ID")}, inverseJoinColumns = {@JoinColumn(name = "VET_ID")})
    private List<Vet> vets = new ArrayList<>();

}
