package com.me.vetclinic.controller;

import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/petowner")
public class PetOwnerRestController {

    private PetOwnerService petOwnerService;

    @Autowired
    public PetOwnerRestController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{petOwnerId}")
    PetOwner getPetOwner(@PathVariable Long petOwnerId) {
        return petOwnerService.findById(petOwnerId);
    }
}
