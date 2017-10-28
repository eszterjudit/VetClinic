package com.me.vetclinic.repository;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Address;
import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@Transactional
public class ClinicRepositoryTest {

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    VetRepository vetRepository;



}
