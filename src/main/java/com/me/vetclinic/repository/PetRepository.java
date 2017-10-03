package com.me.vetclinic.repository;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.domain.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {
    List<Pet> findByType(PetType type);
}
