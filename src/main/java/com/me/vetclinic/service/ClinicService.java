package com.me.vetclinic.service;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Clinic> findByCity(String city) {
        return clinicRepository.getClinicByAddress_City(city);
    }

    public void addVetToClinic(Long clinicId, Long vetId) {
        Clinic clinic = clinicRepository.findOne(clinicId);
        Vet vet = vetRepository.findOne(vetId);
        clinic.getVets().add(vet);
        clinicRepository.save(clinic);
    }

    public void removeVetFromClinic(Long clinicId, Long vetId) {
        Clinic clinic = clinicRepository.findOne(clinicId);
        Vet vet = vetRepository.findOne(vetId);
        vet.getClinics().remove(clinic);
        clinic.getVets().remove(vet);
        clinicRepository.save(clinic);
    }

    public Set<Clinic> findByPetType(PetType petType) {
        List<Vet> allVets = vetRepository.findAll();
        Set<Clinic> clinicsByType = new HashSet<>();
        for(Vet vet : allVets) {
            if(vet.getSpeciality().contains(petType)) {
                clinicsByType.addAll(vet.getClinics());
            }
        }

        return clinicsByType;
    }

}