package com.me.vetclinic.repository;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Clinic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@Transactional
public class ClinicRepositoryTest {

    @Autowired
    ClinicRepository clinicRepository;

    @Test
    public void testSaveClinic() {
        Clinic clinic = new Clinic();
        clinic.setName("First Clinic");
        clinic.setAddress("1234 Budapest Clinic st 1/A");

        clinicRepository.save(clinic);

        assertNotNull(clinic.getId());

        Clinic fetchedClinic = clinicRepository.findOne(clinic.getId());
        assertEquals(fetchedClinic, clinic);

        fetchedClinic.setName("updated name");
        fetchedClinic.setAddress("updated address");
        clinicRepository.save(fetchedClinic);
        Clinic updatedClinic = clinicRepository.findOne(fetchedClinic.getId());
        assertEquals(updatedClinic, fetchedClinic);
    }

}
