package com.me.vetclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Entity
public class Vet extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PetTypes", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<PetType> speciality;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "VET_ID")}, inverseJoinColumns = {@JoinColumn(name = "CLINIC_ID")})
    private List<Clinic> clinics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PetType> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(List<PetType> speciality) {
        this.speciality = speciality;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", clinics=" + clinics.stream().map(clinic -> clinic.getName()).collect(Collectors.toList()) +
                '}';
    }
}
