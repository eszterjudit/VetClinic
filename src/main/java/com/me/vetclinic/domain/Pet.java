package com.me.vetclinic.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.vetclinic.util.JsonDateDeserializer;
import com.me.vetclinic.util.JsonDateSerializer;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;
    private double weight;
    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using=JsonDateDeserializer.class)
    private Date dateOfBirth;

    @ManyToOne(cascade = CascadeType.ALL)
    private PetOwner petOwner;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", dateOfBirth=" + dateOfBirth +
                '}';
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
        return Objects.hash(name, id, dateOfBirth, weight, type);
    }
}
