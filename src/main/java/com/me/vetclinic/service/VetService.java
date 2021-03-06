package com.me.vetclinic.service;

import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {

    private VetRepository vetRepository;

    @Autowired
    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public void addVet(Vet vet) {
        vetRepository.save(vet);
    }

    public void updateVet(Vet vet) {
        vetRepository.save(vet);
    }

    public List<Vet> findBySpeciality(PetType type) {
        return vetRepository.findBySpecialityContains(type);
    }

    public Vet findById(Long vetId) {
        return vetRepository.findOne(vetId);
    }

    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    public Vet findByEmail(String email) {
        return vetRepository.findByEmail(email);
    }
}