package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vet")
public class VetRestController {

    private VetRepository vetRepository;

    @Autowired
    public VetRestController(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addVet(@RequestBody Vet input, UriComponentsBuilder ucBuilder){
        vetRepository.save(input);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/vet/{vetId}").buildAndExpand(input.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{vetId}")
    Vet getVet(@PathVariable Long vetId){
        return vetRepository.findOne(vetId);
    }

}
