package com.me.vetclinic.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PetOwner extends User {

    @Id
    @SequenceGenerator(name="petowner_generator", sequenceName="petowner_sequence", initialValue = 6)
    @GeneratedValue(generator = "petowner_generator")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petOwner", orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

}
