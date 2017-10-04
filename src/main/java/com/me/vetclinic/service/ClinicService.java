package com.me.vetclinic.service;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public void addClinic(Clinic clinic) {
        clinicRepository.save(clinic);
    }

    public Clinic findById(Long clinicId) {
        return clinicRepository.findOne(clinicId);
    }

    public List<Clinic> findAll() { return clinicRepository.findAll(); }

    public List<Clinic> findByZip(int zip) { return clinicRepository.getClinicByAddress_Zip(zip); }

    public List<Clinic> findByCity(String city) { return clinicRepository.getClinicByAddress_City(city); }

}