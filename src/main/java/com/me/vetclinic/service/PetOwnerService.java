package com.me.vetclinic.service;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.repository.PetOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerService {

    private PetOwnerRepository petOwnerRepository;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    public void addPetOwner(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }

    public PetOwner findById(Long petOwnerId) {
        return petOwnerRepository.findOne(petOwnerId);
    }

    public PetOwner findByEmail(String email) {
        return petOwnerRepository.findByEmail(email).isPresent() ? petOwnerRepository.findByEmail(email).get() : null;
    }

    public void updateUser(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }

    public void addPet(Long petOwnerId, Pet pet) {
        petOwnerRepository.findOne(petOwnerId).getPets().add(pet);
    }

    public void deletePet(Long petOwnerId, Pet pet) {
        petOwnerRepository.findOne(petOwnerId).getPets().remove(pet);
    }

}
