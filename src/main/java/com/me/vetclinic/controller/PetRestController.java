package com.me.vetclinic.controller;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.service.PetOwnerService;
import com.me.vetclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.PUT, value = "/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable("petId") long petId, @RequestBody Pet pet) {
        Pet fetchedPet = petService.findById(petId);
        pet.setId(petId);
        pet.setPetOwner(fetchedPet.getPetOwner());
        petService.updatePet(pet);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

}
