package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
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

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addClinic(@RequestBody Clinic clinic, UriComponentsBuilder ucBuilder) {
        clinicService.addClinic(clinic);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/clinic/{clinicId}").buildAndExpand(clinic.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{clinicId}/vets")
    List<Vet> getClinicAllVets(@PathVariable Long clinicId) {
        return vetRepository.findByClinics_Id(clinicId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{clinicId}")
    Clinic getClinic(@PathVariable Long clinicId) {
        return clinicService.findById(clinicId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{city}")
    List<Clinic> getClinicsByCity(@PathVariable String city) {
        return clinicService.findByCity(city);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{zip}")
    List<Clinic> getClinicsByZip(@PathVariable int zip) {
        return clinicService.findByZip(zip);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<Clinic> getAllClinics() {
        return clinicService.findAll();
    }

}
