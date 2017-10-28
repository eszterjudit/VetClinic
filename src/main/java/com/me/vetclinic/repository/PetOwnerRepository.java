package com.me.vetclinic.repository;

import com.me.vetclinic.domain.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
    PetOwner findByEmail(String email);
}
