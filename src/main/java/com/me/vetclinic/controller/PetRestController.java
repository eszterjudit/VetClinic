package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.service.PetOwnerService;
import com.me.vetclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetRestController {

    private PetService petService;
    private PetOwnerService petOwnerService;

    @Autowired
    public PetRestController(PetService petService, PetOwnerService petOwnerService) {
        this.petService = petService;
        this.petOwnerService = petOwnerService;
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{petId}")
    ResponseEntity<?> deletePet(@PathVariable Long petId) {
        Pet pet = petService.findById(petId);
        if (pet == null) {
            return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
        }
        petService.deletePet(pet);
        return new ResponseEntity<Pet>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{petId}")
    Pet getPet(@PathVariable Long petId) {
        return petService.findById(petId);
    }

}
