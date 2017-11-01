package com.me.vetclinic.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.vetclinic.util.JsonHourDeserializer;
import com.me.vetclinic.util.JsonHourSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Clinic {

    @Id
    @SequenceGenerator(name="clinic_generator", sequenceName="clinic_generator", initialValue = 8)
    @GeneratedValue(generator = "clinic_generator")
    @Column(name = "CLINIC_ID")
    private Long id;

    @Column(nullable = false)
    private String clinicName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(nullable = false)
    @JsonSerialize(using=JsonHourSerializer.class)
    @JsonDeserialize(using=JsonHourDeserializer.class)
    @Temporal(TemporalType.DATE)
    private Date openingHour;

    @Column(nullable = false)
    @JsonSerialize(using=JsonHourSerializer.class)
    @JsonDeserialize(using=JsonHourDeserializer.class)
    @Temporal(TemporalType.DATE)
    private Date closingHour;

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {@JoinColumn(name = "CLINIC_ID")}, inverseJoinColumns = {@JoinColumn(name = "VET_ID")})
    */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "VET_CLINIC", joinColumns = {
            @JoinColumn(name = "VET_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "CLINIC_ID", nullable = false, updatable = false) })
    private List<Vet> vets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
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
                ", clinicName='" + clinicName + '\'' +
                ", address=" + address +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                ", vets=" + vets.stream().map(vet -> vet.getEmail()).collect(Collectors.toList()) +
                '}';
    }
}
