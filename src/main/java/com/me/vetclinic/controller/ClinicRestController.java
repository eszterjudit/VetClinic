package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.VetRepository;
import com.me.vetclinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/clinic")
public class ClinicRestController {

    private ClinicService clinicService;
    private VetRepository vetRepository;

    @Autowired
    public ClinicRestController(ClinicService clinicService, VetRepository vetRepository) {
        this.clinicService = clinicService;
        this.vetRepository = vetRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value="/{vetId}")
    ResponseEntity<?> addClinic(@RequestBody Clinic clinic, @PathVariable Long vetId, UriComponentsBuilder ucBuilder) {
        Vet vet = vetRepository.findOne(vetId);
        clinic.getVets().add(vet);
        clinicService.addClinic(clinic);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/clinic/{clinicId}").buildAndExpand(clinic.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value="/{clinicId}/addVet")
    ResponseEntity<?> addVetToClinic(@RequestBody Long vetId, @PathVariable Long clinicId) {
        clinicService.addVetToClinic(clinicId, vetId);
        Clinic clinic = clinicService.findById(clinicId);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value="/{clinicId}/removeVet")
    ResponseEntity<?> removeVetFromClinic(@RequestBody Long vetId, @PathVariable Long clinicId) {
        clinicService.removeVetFromClinic(clinicId, vetId);
        Clinic clinic = clinicService.findById(clinicId);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{city}/{petType}/{onlyShowOpenClinics}")
    List<Clinic> filterClinics(@PathVariable String city, @PathVariable PetType petType, @PathVariable boolean onlyShowOpenClinics) {
        List<Clinic> allClinics = clinicService.findAll();
        if(!city.isEmpty()) {
            List<Clinic> clinicsByCity = clinicService.findByCity(city);
            allClinics.clear();
            allClinics.addAll(clinicsByCity);
        }
        if(petType != null) {
            Set<Clinic> clinicsByPetType = clinicService.findByPetType(petType);
            allClinics.clear();
            allClinics.addAll(clinicsByPetType);
        }
        if(onlyShowOpenClinics == true) {
            for(Iterator<Clinic> iterator = allClinics.iterator(); iterator.hasNext();) {
                Clinic clinic = iterator.next();
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime openingHour = LocalTime.parse(clinic.getOpeningHour(), formatter);
                LocalTime closingHour = LocalTime.parse(clinic.getClosingHour(), formatter);
                if(!(openingHour.isBefore(currentTime) && closingHour.isAfter(currentTime))) {
                    iterator.remove();
                }
            }
        }

        return allClinics;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{clinicId}/vets")
    List<Vet> getClinicAllVets(@PathVariable Long clinicId) {
        return vetRepository.findByClinics_Id(clinicId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{clinicId}")
    Clinic getClinic(@PathVariable Long clinicId) {
        return clinicService.findById(clinicId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/by/{city}")
    List<Clinic> getClinicsByCity(@PathVariable String city) {
        return clinicService.findByCity(city);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Clinic> getAllClinics() {
        return clinicService.findAll();
    }

}
