package com.me.vetclinic.service;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void addPet(Pet pet) {
        petRepository.save(pet);
    }

    public void deletePet(Pet pet) {
        petRepository.delete(pet);
    }

    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }

    public Pet findById(Long petId) {
        return petRepository.findOne(petId);
    }
}
