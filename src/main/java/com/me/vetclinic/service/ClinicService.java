package com.me.vetclinic.service;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    public static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("HH:mm");

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

    public Set<PetType> getClinicTypes(Long clinicId) {
        Clinic clinic = clinicRepository.findOne(clinicId);
        return clinic.getVets().stream().map(vet -> vet.getSpeciality()).flatMap(speciality -> speciality.stream()).collect(Collectors.toSet());
    }

    public boolean isClinicOpen(Long clinicId) {
        Clinic clinic = clinicRepository.findOne(clinicId);
        return LocalTime.parse(clinic.getOpeningHour(),TIME_PATTERN).isBefore(LocalTime.now()) && LocalTime.parse(clinic.getClosingHour(),TIME_PATTERN).isAfter(LocalTime.now());
    }

}