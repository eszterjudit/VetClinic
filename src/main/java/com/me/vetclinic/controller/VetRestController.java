package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.PUT, value = "/{vetId}")
    public ResponseEntity<?> updateVet(@PathVariable("vetId") long vetId, @RequestBody Vet vet) {
        Vet fetchedVet = getVet(vetId);
        vet.setId(vetId);
        vet.setClinics(fetchedVet.getClinics());
        vetService.updateVet(vet);
        return new ResponseEntity<>(vet, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vetId}/clinics")
    List<Clinic> getVetAllClinics(@PathVariable Long vetId) {
        return clinicRepository.findByVets_Id(vetId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{vetId}")
    Vet getVet(@PathVariable Long vetId) {
        return vetService.findById(vetId);
    }

}
