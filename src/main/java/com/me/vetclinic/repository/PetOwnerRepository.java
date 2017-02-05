package com.me.vetclinic.repository;

import com.me.vetclinic.domain.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner,Long> {
}
