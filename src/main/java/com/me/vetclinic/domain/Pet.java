package com.me.vetclinic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.vetclinic.util.JsonDateDeserializer;
import com.me.vetclinic.util.JsonDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private PetType petType;

    @Column
    private String petName;

    @Column
    private double petWeight;

    @Column
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @JsonIgnore
    @ManyToOne
    private PetOwner petOwner;

}
