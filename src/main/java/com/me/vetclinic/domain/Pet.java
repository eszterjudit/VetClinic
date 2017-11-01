package com.me.vetclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @SequenceGenerator(name="pet_generator", sequenceName="pet_sequence", initialValue = 16)
    @GeneratedValue(generator = "pet_generator")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PetType petType;

    @Column(nullable = false)
    private String petName;

    @Column(nullable = false)
    private double petWeight;

    @Column(nullable = false)
    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using=JsonDateDeserializer.class)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @JsonIgnore
    @ManyToOne
    private PetOwner petOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public double getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(double petWeight) {
        this.petWeight = petWeight;
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
                ", petType=" + petType +
                ", petName='" + petName + '\'' +
                ", petWeight=" + petWeight +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (Double.compare(pet.petWeight, petWeight) != 0) return false;
        if (id != null ? !id.equals(pet.id) : pet.id != null) return false;
        if (petType != pet.petType) return false;
        if (petName != null ? !petName.equals(pet.petName) : pet.petName != null) return false;
        return dateOfBirth != null ? dateOfBirth.equals(pet.dateOfBirth) : pet.dateOfBirth == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petName, id, dateOfBirth, petWeight, petType);
    }
}
