package com.me.vetclinic.repository;

import com.me.vetclinic.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
    List<Vet> findBySpecialityContains(PetType type);
    List<Vet> findByClinics_Id(Long clinicId);
    Optional<Vet> findByEmail(String email);
}
