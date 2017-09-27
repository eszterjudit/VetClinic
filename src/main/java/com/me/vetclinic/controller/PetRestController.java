package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by totheszter on 2017. 09. 24..
 */
@RestController
@RequestMapping("/pet")
public class PetRestController {

    private PetRepository petRepository;

    @Autowired
    public PetRestController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addPet(@RequestBody Pet input, UriComponentsBuilder ucBuilder){
        petRepository.save(input);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pet/{petId}").buildAndExpand(input.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{petId}")
    Pet getPet(@PathVariable Long petId){
        return petRepository.findOne(petId);
    }
}
