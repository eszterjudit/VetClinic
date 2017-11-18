package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
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

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(method = RequestMethod.GET)
    List<Clinic> getAllClinics(@RequestParam(value = "city", required = false) String city,
                               @RequestParam(value = "petType", required = false) PetType petType,
                               @RequestParam(value = "onlyOpen", required = false, defaultValue = "false") boolean onlyOpen) {
        List<Clinic> allClinics = clinicService.findAll();
        if(city != null && !city.isEmpty()) {
            allClinics = allClinics.stream().filter(clinic -> clinic.getAddress().getCity().toLowerCase().equals(city.toLowerCase())).collect(Collectors.toList());
        }
        if(petType != null) {
            allClinics = allClinics.stream().filter(clinic -> clinicService.getClinicTypes(clinic.getId()).contains(petType)).collect(Collectors.toList());
        }
        if(onlyOpen == true) {
            allClinics = allClinics.stream().filter(clinic -> clinicService.isClinicOpen(clinic.getId())).collect(Collectors.toList());
        }
        return allClinics;
    }

}
