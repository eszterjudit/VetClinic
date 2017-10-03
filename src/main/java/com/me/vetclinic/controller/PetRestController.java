package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.repository.PetRepository;
import com.me.vetclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by totheszter on 2017. 09. 24..
 */
@RestController
@RequestMapping("/pet")
public class PetRestController {

    private PetService petService;

    @Autowired
    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addPet(@RequestBody Pet pet, UriComponentsBuilder ucBuilder){
        petService.addPet(pet);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pet/{petId}").buildAndExpand(pet.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{petId}")
    Pet getPet(@PathVariable Long petId){
        return petService.findById(petId);
    }

}
