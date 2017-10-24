package com.me.vetclinic.service;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private ClinicRepository clinicRepository;
    private VetRepository vetRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository, VetRepository vetRepository) {
        this.clinicRepository = clinicRepository;
        this.vetRepository = vetRepository;
    }

    public void addClinic(Clinic clinic) {
        clinicRepository.save(clinic);
    }

    public Clinic findById(Long clinicId) {
        return clinicRepository.findOne(clinicId);
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public List<Clinic> findByZip(int zip) {
        return clinicRepository.getClinicByAddress_Zip(zip);
    }

    public List<Clinic> findByCity(String city) {
        return clinicRepository.getClinicByAddress_City(city);
    }

    public void addVetToClinic(Long clinicId, Long vetId) {
        Clinic clinic = clinicRepository.findOne(clinicId);
        Vet vet = vetRepository.findOne(vetId);
        clinic.getVets().add(vet);
        vet.getClinics().add(clinic);
        clinicRepository.save(clinic);
        vetRepository.save(vet);
    }

    public void removeVetFromClinic(Long clinicId, Long vetId) {
        Clinic clinic = clinicRepository.findOne(clinicId);
        Vet vet = vetRepository.findOne(vetId);
        clinic.getVets().remove(vet);
        vet.getClinics().remove(clinic);
        clinicRepository.save(clinic);
        vetRepository.save(vet);
    }

}