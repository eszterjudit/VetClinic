package com.me.vetclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class Vet extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VET_ID")
    private Long id;

    @ElementCollection(targetClass = PetType.class)
    @CollectionTable(
            name="specialities",
            joinColumns=@JoinColumn(name="VET_ID")
    )
    @Column(name = "PET_TYPE_ID")
    private Set<PetType> speciality;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "VET_ID")}, inverseJoinColumns = {@JoinColumn(name = "CLINIC_ID")})
    private List<Clinic> clinics = new ArrayList<>();


}
