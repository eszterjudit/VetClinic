package com.me.vetclinic.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Entity
@Table(name = "pet_owner")
public class PetOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "petOwner")
    private List<Pet> pets = new ArrayList<Pet>();

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

    @Override
    public String toString() {
        return "PetOwner{" +
                "id=" + id +
                "name=" + getFirstName() + " " + getLastName() +
                '}';
    }
}
