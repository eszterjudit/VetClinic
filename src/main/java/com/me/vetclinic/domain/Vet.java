package com.me.vetclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Vet extends User {

    @Id
    @SequenceGenerator(name="vet_generator", sequenceName="vet_sequence", initialValue = 7)
    @GeneratedValue(generator = "vet_generator")
    @Column(name = "VET_ID")
    private Long id;

    @ElementCollection(targetClass = PetType.class)
    @CollectionTable(
            name="specialities",
            joinColumns=@JoinColumn(name="VET_ID")
    )
    @Column(name = "PET_TYPE_ID")
    private Set<PetType> speciality;

    /*
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "VET_ID")}, inverseJoinColumns = {@JoinColumn(name = "CLINIC_ID")})
    */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "vets")
    private List<Clinic> clinics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PetType> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Set<PetType> speciality) {
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
                ", clinics=" + clinics.stream().map(clinic -> clinic.getClinicName()).collect(Collectors.toList()) +
                '}';
    }
}
