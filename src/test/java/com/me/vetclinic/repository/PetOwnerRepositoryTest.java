package com.me.vetclinic.repository;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.domain.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@Transactional
public class PetOwnerRepositoryTest {
    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Autowired
    PetRepository petRepository;

    @Test
    public void testSavePetOwner() {
        Pet cat = new Pet();
        cat.setType(PetType.CAT);
        Pet dog = new Pet();
        dog.setType(PetType.DOG);

        List<Pet> pets = new ArrayList<>();
        pets.add(cat);
        pets.add(dog);

        petRepository.save(pets);

        PetOwner petOwner = new PetOwner();
        petOwner.setEmail("test@test.com");
        petOwner.setFirstName("testFirstName");
        petOwner.setLastName("testLastName");
        petOwner.setPets(pets);

        petOwnerRepository.save(petOwner);
        assertNotNull(petOwner.getId());

        PetOwner fetchedPetOwner = petOwnerRepository.findOne(petOwner.getId());
        assertEquals(fetchedPetOwner,petOwner);
        assertEquals(2, fetchedPetOwner.getPets().size());

        assertTrue(fetchedPetOwner.getPets().contains(cat));
        assertTrue(fetchedPetOwner.getPets().contains(dog));

        fetchedPetOwner.setFirstName("updatedFirstName");
        petOwnerRepository.save(fetchedPetOwner);
        PetOwner updatedPetOwner = petOwnerRepository.findOne(fetchedPetOwner.getId());
        assertEquals(updatedPetOwner,fetchedPetOwner);
    }

}
