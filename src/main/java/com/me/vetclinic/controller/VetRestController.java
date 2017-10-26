package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.service.VetService;
import com.sun.org.apache.regexp.internal.RE;
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

    private VetService vetService;
    private ClinicRepository clinicRepository;

    @Autowired
    public VetRestController(VetService vetService, ClinicRepository clinicRepository) {
        this.vetService = vetService;
        this.clinicRepository = clinicRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addVet(@RequestBody Vet vet, UriComponentsBuilder ucBuilder) {
        vetService.addVet(vet);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/vet/{vetId}").buildAndExpand(vet.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{vetId}")
    public ResponseEntity<?> updateVet(@PathVariable("vetId") long vetId, @RequestBody Vet vet) {
        vet.setId(vetId);
        vetService.updateVet(vet);
        return new ResponseEntity<>(vet, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vetId}/clinics")
    List<Clinic> getVetAllClinics(@PathVariable Long vetId) {
        return clinicRepository.findByVets_Id(vetId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/speciality/{petType}")
    List<Vet> getVetsBySpeciality(@PathVariable PetType type) {
        return vetService.findBySpeciality(type);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vetId}")
    Vet getVet(@PathVariable Long vetId) {
        return vetService.findById(vetId);
    }

}
