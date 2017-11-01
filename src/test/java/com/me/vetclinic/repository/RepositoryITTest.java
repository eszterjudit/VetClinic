package com.me.vetclinic.repository;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@Transactional
public class RepositoryITTest {

    @Autowired ClinicRepository clinicRepository;
    @Autowired VetRepository vetRepository;
    @Autowired PetRepository petRepository;
    @Autowired PetOwnerRepository petOwnerRepository;
    @Autowired AddressRepository addressRepository;

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
    public void testClinicRepository() {

        // save
        assertNotNull(clinic1.getId());

        // fetch
        Clinic fetchedClinic = clinicRepository.findOne(clinic1.getId());
        assertEquals(fetchedClinic, clinic1);

        // update
        fetchedClinic.setClinicName("updated name");
        clinicRepository.save(fetchedClinic);
        Clinic updatedClinic = clinicRepository.findOne(fetchedClinic.getId());
        assertEquals(updatedClinic, fetchedClinic);

        // get clinic by city
        String city1 = "Budapest";
        String city2 = "asdasd";

        List<Clinic> fetchedClinics = clinicRepository.getClinicByAddress_City(city1);

        assertEquals(1,fetchedClinics.size());
        assertEquals(city1, fetchedClinics.get(0).getAddress().getCity());

        List<Clinic> fetchedClinics2 = clinicRepository.getClinicByAddress_City(city2);

        assertTrue(fetchedClinics2.isEmpty());

        // get all clinics for vet
        List<Vet> vetList = new ArrayList<>();
        vetList.add(vet1);

        clinic1.setVets(vetList);

        clinicRepository.save(clinic1);

        List<Clinic> clinicsForVet = clinicRepository.findByVets_Id(vet1.getId());

        assertEquals(1, clinicsForVet.size());
        assertEquals(clinicsForVet.get(0).getClinicName(), clinic1.getClinicName());

        List<Clinic> noClinics = clinicRepository.findByVets_Id(vet2.getId());

        assertTrue(noClinics.isEmpty());

        clinic2.setVets(vetList);
        clinicRepository.save(clinic2);

        List<Vet> vetListForClinic = vetRepository.findByClinics_Id(clinic1.getId());

        assertEquals(1, vetListForClinic.size());
        assertTrue(vetListForClinic.contains(vet1));

    }

    @Test
    public void testVetRepository() {

        // save
        assertNotNull(vet1.getId());

        // fetch
        Vet fetchedVet = vetRepository.findOne(vet1.getId());
        assertEquals(fetchedVet, vet1);

        // update
        fetchedVet.setFirstName("updated");
        vetRepository.save(fetchedVet);
        Vet updatedVet = vetRepository.findOne(fetchedVet.getId());
        assertEquals(updatedVet, fetchedVet);

        // get vet by speciality
        List<Vet> dogVets = vetRepository.findBySpecialityContains(PetType.DOG);
        assertEquals(1, dogVets.size());

        assertTrue(dogVets.contains(vet1));
        assertFalse(dogVets.contains(vet2));

        // get vet by email
        String email = "vet1@email.com";
        Vet vetByEmail = vetRepository.findByEmail(email);

        assertNotNull(vetByEmail);
        assertEquals(vet1, vetByEmail);

        // get vets by clinic id
        List<Clinic> clinics = new ArrayList<>();
        clinics.add(clinic1);
        vet1.setClinics(clinics);

        vetRepository.save(vet1);

        List<Vet> vetsByClinicId = vetRepository.findByClinics_Id(clinic1.getId());
        List<Clinic> clinicsByVetId = clinicRepository.findByVets_Id(vet1.getId());

        assertEquals(1, vetsByClinicId.size());
        assertTrue(vetsByClinicId.contains(vet1));
        assertFalse(vetsByClinicId.contains(vet2));

        assertTrue(clinicsByVetId.contains(clinic1));

    }

    @Test
    public void testPetOwnerRepository() {

        // save
        assertNotNull(petOwner1.getId());

        // fetch
        PetOwner fetchedPetOwner = petOwnerRepository.findOne(petOwner1.getId());
        assertEquals(fetchedPetOwner, petOwner1);

        // update
        fetchedPetOwner.setFirstName("updatedPetOwner");
        petOwnerRepository.save(fetchedPetOwner);
        PetOwner updatedPetOwner = petOwnerRepository.findOne(fetchedPetOwner.getId());
        assertEquals(updatedPetOwner, fetchedPetOwner);

        // get petowner by email
        String email = "petowner1@email.com";
        PetOwner petOwnerByEmail = petOwnerRepository.findByEmail(email);

        assertNotNull(petOwnerByEmail);
        assertEquals(petOwner1, petOwnerByEmail);

        // delete pet
        List<Pet> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);
        petOwner1.setPets(petList);
        petOwnerRepository.save(petOwner1);


        assertEquals(2, petOwnerRepository.getOne(petOwner1.getId()).getPets().size());

        petList.remove(pet1);
        petOwner1.setPets(petList);
        petRepository.delete(pet1.getId());

        assertFalse(petRepository.exists(pet1.getId()));

        assertEquals(1, petOwnerRepository.getOne(petOwner1.getId()).getPets().size());
        assertEquals(pet2, petOwnerRepository.getOne(petOwner1.getId()).getPets().get(0));

    }

    @Test
    public void testPetRepository() {

        // save
        assertNotNull(pet1.getId());

        // fetch
        Pet fetchedPet = petRepository.findOne(pet1.getId());
        assertEquals(fetchedPet, pet1);

        // update
        fetchedPet.setPetName("updatedpet");
        petRepository.save(fetchedPet);
        Pet updatedPet = petRepository.findOne(fetchedPet.getId());
        assertEquals(updatedPet, fetchedPet);

        // find pet by type
        PetType dogType = PetType.DOG;

        List<Pet> petsByType = petRepository.findByPetType(dogType);

        assertEquals(1, petsByType.size());
        assertTrue(petsByType.contains(pet2));
        assertFalse(petsByType.contains(pet1));

    }


    // BEANS

    public Vet vet1() {
        Vet vet1 = new Vet();
        vet1.setFirstName("Vet 1");
        vet1.setLastName("Vet");
        vet1.setEmail("vet1@email.com");
        vet1.setPhone("1234567");
        vet1.setSpeciality(specialities1());
        return vet1;
    }

    public Vet vet2() {
        Vet vet2 = new Vet();
        vet2.setFirstName("Vet 2");
        vet2.setLastName("Vet");
        vet2.setEmail("vet2@email.com");
        vet2.setPhone("7654321");
        vet2.setSpeciality(specialities2());
        return vet2;
    }

    public Set<PetType> specialities1() {
        Set<PetType> specialities1 = new HashSet<>();
        specialities1.add(PetType.CAT);
        specialities1.add(PetType.DOG);
        return specialities1;
    }

    public Set<PetType> specialities2() {
        Set<PetType> specialities2 = new HashSet<>();
        specialities2.add(PetType.RODENT);
        specialities2.add(PetType.REPTILE);
        specialities2.add(PetType.BIRD);
        return specialities2;
    }

    public Clinic clinic1() {
        Clinic clinic1 = new Clinic();
        clinic1.setClinicName("First Clinic");
        clinic1.setOpeningHour("08:30");
        clinic1.setClosingHour("16:00");
        clinic1.setAddress(address1());
        return clinic1;
    }

    public Clinic clinic2() {
        Clinic clinic2 = new Clinic();
        clinic2.setClinicName("Second Clinic");
        clinic2.setOpeningHour("08:30");
        clinic2.setClosingHour("16:00");
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
        pet1.setPetName("Bruno");
        pet1.setPetType(PetType.CAT);
        pet1.setPetWeight(3);
        pet1.setDateOfBirth(birthday1());
        return pet1;
    }

    public Pet pet2() {
        Pet pet1 = new Pet();
        pet1.setPetName("Kutya");
        pet1.setPetType(PetType.DOG);
        pet1.setPetWeight(10);
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
