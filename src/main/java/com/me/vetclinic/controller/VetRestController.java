package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Pet;
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
@RequestMapping("/vet")
public class VetRestController {

    private VetRepository vetRepository;
    private ClinicRepository clinicRepository;

    @Autowired
    public VetRestController(VetRepository vetRepository, ClinicRepository clinicRepository) {
        this.vetRepository = vetRepository;
        this.clinicRepository = clinicRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addVet(@RequestBody Vet input, UriComponentsBuilder ucBuilder){
        vetRepository.save(input);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/vet/{vetId}").buildAndExpand(input.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{vetId}/clinics")
    List<Clinic> getVetAllClinics(@PathVariable Long vetId){
        return clinicRepository.findByVets_Id(vetId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{vetId}")
    Vet getVet(@PathVariable Long vetId){
        return vetRepository.findOne(vetId);
    }

}
