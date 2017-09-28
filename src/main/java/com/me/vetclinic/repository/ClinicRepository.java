package com.me.vetclinic.repository;

import com.me.vetclinic.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic,Long> {
}
