package com.me.vetclinic.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PetOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "id=" + id +
                "name=" + getFirstName() + " " + getLastName() +
                '}';
    }
}
