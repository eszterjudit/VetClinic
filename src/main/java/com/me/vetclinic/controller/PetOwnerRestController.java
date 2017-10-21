package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.service.PetOwnerService;
import com.me.vetclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/petOwner")
public class PetOwnerRestController {

    private PetOwnerService petOwnerService;
    private PetService petService;

    @Autowired
    public PetOwnerRestController(PetOwnerService petOwnerService, PetService petService) {
        this.petOwnerService = petOwnerService;
        this.petService = petService;
    }

    @RequestMapping(method = RequestMethod.POST, value="/{petOwnerId}/addPet")
    ResponseEntity<?> addPet(@RequestBody Pet pet, @PathVariable Long petOwnerId, UriComponentsBuilder ucBuilder) {
        PetOwner petOwner = petOwnerService.findById(petOwnerId);
        petService.addPet(pet);
        petOwnerService.addPet(petOwnerId, pet);
        petOwnerService.addPetOwner(petOwner);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pet/{petId}").buildAndExpand(pet.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{petOwnerId}")
    PetOwner getPetOwner(@PathVariable Long petOwnerId) {
        return petOwnerService.findById(petOwnerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{petOwnerId}/pets")
    List<Pet> getPetOwnerAllPets(@PathVariable Long petOwnerId) {
        return petOwnerService.findById(petOwnerId).getPets();
    }

}
