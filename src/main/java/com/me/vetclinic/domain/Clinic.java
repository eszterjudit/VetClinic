package com.me.vetclinic.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLINIC_ID")
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private LocalTime openingHour;
    private LocalTime closingHour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "CLINIC_ID")}, inverseJoinColumns = {@JoinColumn(name = "VET_ID")})
    private List<Vet> vets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalTime getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(LocalTime openingHour) {
        this.openingHour = openingHour;
    }

    public LocalTime getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(LocalTime closingHour) {
        this.closingHour = closingHour;
    }

    public List<Vet> getVets() {
        return vets;
    }

    public void setVets(List<Vet> vets) {
        this.vets = vets;
    }


}
