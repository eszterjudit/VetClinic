package com.me.vetclinic.controller;


import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.repository.PetOwnerRepository;
import com.me.vetclinic.repository.PetRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/petowner")
public class PetOwnerRestController {

    private Logger log = Logger.getLogger(PetOwnerRestController.class);

    private PetOwnerRepository petOwnerRepository;
    private PetRepository petRepository;

    @Autowired
    public PetOwnerRestController(PetOwnerRepository petOwnerRepository, PetRepository petRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.petRepository = petRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{petOwnerId}")
    public List<Pet> getOwnerAllPets(@PathVariable Long petOwnerId){
        PetOwner petOwner = petOwnerRepository.findOne(petOwnerId);
        log.info(petOwner);
        List<Pet> pets = petRepository.findByPetOwner(petOwner);
        log.info(pets);
        return pets;
    }
}
