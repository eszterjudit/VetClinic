package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.service.PetOwnerService;
import com.me.vetclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pet")
public class PetRestController {

    private PetService petService;
    private PetOwnerService petOwnerService;

    @Autowired
    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addPet(@RequestBody Pet pet, UriComponentsBuilder ucBuilder) {
        petService.addPet(pet);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pet/{petId}").buildAndExpand(pet.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> deletePet(@PathVariable Long petId) {
        Pet pet = petService.findById(petId);
        if (pet == null) {
            return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
        }
        petService.deletePet(pet);
        return new ResponseEntity<Pet>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable("petId") long petId, @RequestBody Pet pet) {
        Pet currentPet = petService.findById(petId);

        if (currentPet == null) {
            return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
        }

        currentPet.setName(pet.getName());
        currentPet.setDateOfBirth(pet.getDateOfBirth());
        currentPet.setType(pet.getType());
        currentPet.setWeight(pet.getWeight());

        petService.updatePet(currentPet);
        return new ResponseEntity<Pet>(currentPet, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{petId}")
    Pet getPet(@PathVariable Long petId) {
        return petService.findById(petId);
    }

}
