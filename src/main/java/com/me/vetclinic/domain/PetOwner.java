package com.me.vetclinic.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class PetOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petOwner", orphanRemoval = true)
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
        this.pets.clear();
        this.pets.addAll(pets);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetOwner petOwner = (PetOwner) o;

        if (id != null ? !id.equals(petOwner.id) : petOwner.id != null) return false;
        return pets != null ? pets.equals(petOwner.pets) : petOwner.pets == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pets != null ? pets.hashCode() : 0);
        return result;
    }
}
