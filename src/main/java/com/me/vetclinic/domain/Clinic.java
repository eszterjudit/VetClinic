package com.me.vetclinic.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.vetclinic.util.JsonDateDeserializer;
import com.me.vetclinic.util.JsonDateSerializer;
import com.me.vetclinic.util.JsonHourDeserializer;
import com.me.vetclinic.util.JsonHourSerializer;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLINIC_ID")
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @JsonSerialize(using=JsonHourSerializer.class)
    @JsonDeserialize(using=JsonHourDeserializer.class)
    private Date openingHour;
    @JsonSerialize(using=JsonHourSerializer.class)
    @JsonDeserialize(using=JsonHourDeserializer.class)
    private Date closingHour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "CLINIC_ID")}, inverseJoinColumns = {@JoinColumn(name = "VET_ID")})
    private List<Vet> vets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Date openingHour) {
        this.openingHour = openingHour;
    }

    public Date getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(Date closingHour) {
        this.closingHour = closingHour;
    }

    public List<Vet> getVets() {
        return vets;
    }

    public void setVets(List<Vet> vets) {
        this.vets = vets;
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                ", vets=" + vets.stream().map(vet -> vet.getEmail()).collect(Collectors.toList()) +
                '}';
    }
}
