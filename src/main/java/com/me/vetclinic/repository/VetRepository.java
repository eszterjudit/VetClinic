package com.me.vetclinic.repository;

import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Repository
public interface VetRepository extends JpaRepository<Vet,Long> {
    public List<Vet> findBySpecialityContains(PetType type);
}