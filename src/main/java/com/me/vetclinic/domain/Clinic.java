package com.me.vetclinic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private Date openingHour;
    private Date closingHour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
}
