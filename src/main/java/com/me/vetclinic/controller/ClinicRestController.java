package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.repository.VetRepository;
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

    private ClinicRepository clinicRepository;
    private VetRepository vetRepository;

    @Autowired
    public ClinicRestController(ClinicRepository clinicRepository, VetRepository vetRepository) {
        this.clinicRepository = clinicRepository;
        this.vetRepository = vetRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addClinic(@RequestBody Clinic input, UriComponentsBuilder ucBuilder){
        clinicRepository.save(input);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/clinic/{clinicId}").buildAndExpand(input.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{clinicId}/vets")
    List<Vet> getClinicAllVets(@PathVariable Long clinicId){
        return vetRepository.findByClinics_Id(clinicId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{clinicId}")
    Clinic getClinic(@PathVariable Long clinicId){
        return clinicRepository.findOne(clinicId);
    }

}
