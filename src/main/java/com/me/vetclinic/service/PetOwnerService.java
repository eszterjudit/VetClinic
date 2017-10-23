package com.me.vetclinic.service;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.repository.PetOwnerRepository;
import com.me.vetclinic.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetOwnerService {

    private PetOwnerRepository petOwnerRepository;
    private PetRepository petRepository;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetRepository petRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.petRepository = petRepository;
    }

    public void addPetOwner(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }

    public PetOwner findById(Long petOwnerId) {
        return petOwnerRepository.findOne(petOwnerId);
    }

    public Optional<PetOwner> findByEmail(String email) {
        return petOwnerRepository.findByEmail(email);
    }

    public void updateUser(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }

    public void addPet(Long petOwnerId, Pet pet) {
        petRepository.save(pet);
        PetOwner petOwner = petOwnerRepository.findOne(petOwnerId);
        petOwner.getPets().add(pet);
        petOwnerRepository.save(petOwner);
    }

    public void deletePet(Long petOwnerId, Pet pet) {
        petOwnerRepository.findOne(petOwnerId).getPets().remove(pet);
    }

}
