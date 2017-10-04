package com.me.vetclinic.repository;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByType(PetType type);
}
