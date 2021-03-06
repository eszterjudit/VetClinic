package com.me.vetclinic.repository;

import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
    List<Vet> findBySpecialityContains(PetType type);
    List<Vet> findByClinics_Id(Long clinicId);
    Vet findByEmail(String email);
}
