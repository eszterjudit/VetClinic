package com.me.vetclinic.repository;

import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    List<Clinic> findByVets_Id(Long vetId);

    List<Clinic> getClinicByAddress_City(String city);
}
