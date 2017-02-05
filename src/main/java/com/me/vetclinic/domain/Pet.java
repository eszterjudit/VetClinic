package com.me.vetclinic.domain;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;
    private double weight;
    private LocalDate dateOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (Double.compare(pet.weight, weight) != 0) return false;
        if (id != null ? !id.equals(pet.id) : pet.id != null) return false;
        if (type != pet.type) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        return dateOfBirth != null ? dateOfBirth.equals(pet.dateOfBirth) : pet.dateOfBirth == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,id,dateOfBirth,weight,type);
    }
}
