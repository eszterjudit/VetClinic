package com.me.vetclinic.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Entity
public class Vet extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PetTypes", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<PetType> speciality;

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
}
