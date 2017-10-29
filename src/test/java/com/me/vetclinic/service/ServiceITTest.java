package com.me.vetclinic.service;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.*;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.repository.PetOwnerRepository;
import com.me.vetclinic.repository.PetRepository;
import com.me.vetclinic.repository.VetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@Transactional
public class ServiceITTest {

    @Autowired ClinicRepository clinicRepository;
    @Autowired VetRepository vetRepository;
    @Autowired PetOwnerRepository petOwnerRepository;
    @Autowired PetRepository petRepository;

    @Autowired ClinicService clinicService;
    @Autowired PetOwnerService petOwnerService;
    @Autowired PetService petService;
    @Autowired VetService vetService;

    Clinic clinic1 = clinic1();
    Clinic clinic2 = clinic2();
    Vet vet1 = vet1();
    Vet vet2 = vet2();
    PetOwner petOwner1 = petOwner1();
    PetOwner petOwner2 = petOwner2();
    Pet pet1 = pet1();
    Pet pet2 = pet2();

    @Before
    public void save() {
        clinicRepository.save(clinic1);
        clinicRepository.save(clinic2);
        vetRepository.save(vet1);
        vetRepository.save(vet2);
        petOwnerRepository.save(petOwner1);
        petOwnerRepository.save(petOwner2);
        petRepository.save(pet1);
        petRepository.save(pet2);
    }

    @Test
    public void testClinicService() {

        assertNotNull(clinic1.getId());
        assertNotNull(vet1.getId());
        // add vet to clinic
        clinicService.addVetToClinic(clinic1.getId(), vet1.getId());
        Clinic fetchedClinic = clinicRepository.findOne(clinic1.getId());

        assertNotNull(fetchedClinic);
        assertTrue(clinic1.getVets().contains(vet1));
    }

    // BEANS

    public Vet vet1() {
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Vet 1");
        vet1.setLastName("Vet");
        vet1.setEmail("vet1@email.com");
        vet1.setPhone("1234567");
        vet1.setAddress(address1());
        vet1.setSpeciality(specialities1());
        return vet1;
    }

    public Vet vet2() {
        Vet vet2 = new Vet();
        vet2.setFirstName("Vet 2");
        vet2.setLastName("Vet");
        vet2.setEmail("vet2@email.com");
        vet2.setPhone("7654321");
        vet2.setAddress(address2());
        vet2.setSpeciality(specialities2());
        return vet2;
    }

    public List<PetType> specialities1() {
        List<PetType> specialities1 = new ArrayList<>();
        specialities1.add(PetType.CAT);
        specialities1.add(PetType.DOG);
        return specialities1;
    }

    public List<PetType> specialities2() {
        List<PetType> specialities2 = new ArrayList<>();
        specialities2.add(PetType.RODENT);
        specialities2.add(PetType.REPTILE);
        specialities2.add(PetType.BIRD);
        return specialities2;
    }

    public Clinic clinic1() {
        Clinic clinic1 = new Clinic();
        clinic1.setId(1L);
        clinic1.setName("First Clinic");
        clinic1.setOpeningHour(openingHour());
        clinic1.setClosingHour(closingHour());
        clinic1.setAddress(address1());
        return clinic1;
    }

    public Clinic clinic2() {
        Clinic clinic2 = new Clinic();
        clinic2.setName("Second Clinic");
        clinic2.setOpeningHour(openingHour());
        clinic2.setClosingHour(closingHour());
        clinic2.setAddress(address2());
        return clinic2;
    }

    public Date openingHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);

        return calendar.getTime();
    }

    public Date closingHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 00);

        return calendar.getTime();
    }

    public Address address1() {
        Address address1 = new Address();
        address1.setCity("Budapest");
        address1.setCountry("Hungary");
        address1.setStreet("Németvölgyi út 20");
        address1.setZip("1126");
        return address1;
    }

    public Address address2() {
        Address address2 = new Address();
        address2.setCity("some city");
        address2.setStreet("street 1/A");
        address2.setZip("1234");
        address2.setCountry("Hungary");
        return address2;
    }

    public PetOwner petOwner1() {
        PetOwner petOwner1 = new PetOwner();
        petOwner1.setFirstName("Pet");
        petOwner1.setLastName("Owner1");
        petOwner1.setEmail("petowner1@email.com");
        petOwner1.setPhone("4352617");
        petOwner1.setAddress(address1());
        return petOwner1;
    }

    public PetOwner petOwner2() {
        PetOwner petOwner2 = new PetOwner();
        petOwner2.setFirstName("Pet");
        petOwner2.setLastName("Owner2");
        petOwner2.setEmail("petowner2@email.com");
        petOwner2.setPhone("9876234");
        petOwner2.setAddress(address2());
        return petOwner2;
    }

    public Pet pet1() {
        Pet pet1 = new Pet();
        pet1.setName("Bruno");
        pet1.setType(PetType.CAT);
        pet1.setWeight(3);
        pet1.setDateOfBirth(birthday1());
        return pet1;
    }

    public Pet pet2() {
        Pet pet1 = new Pet();
        pet1.setName("Kutya");
        pet1.setType(PetType.DOG);
        pet1.setWeight(10);
        pet1.setDateOfBirth(birthday2());
        return pet1;
    }

    public Date birthday1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, 8, 1);
        return calendar.getTime();
    }

    public Date birthday2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, 9, 10);
        return calendar.getTime();
    }

}
