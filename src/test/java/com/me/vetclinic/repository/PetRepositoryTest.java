package com.me.vetclinic.repository;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.util.Assert.notNull;

/**
 * Created by totheszter on 2017. 02. 05..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@Transactional
public class PetRepositoryTest {
    @Autowired
    private PetRepository repository;

    @Test
    public void testSavingPetRepository() {
        Pet pet = new Pet();
        pet.setName("TestPet");
        pet.setDateOfBirth(LocalDate.now());
        pet.setWeight(0.5);

        repository.save(pet);
        notNull(pet.getId());
        Pet fetchedPet = repository.findOne(pet.getId());
        assertEquals(fetchedPet,pet);

        fetchedPet.setName("updatedTestPet");
        repository.save(fetchedPet);
        Pet updatedPet = repository.findOne(fetchedPet.getId());
        assertEquals(fetchedPet,updatedPet);
    }

    @Test
    public void testFindByType() {
        Pet cat = new Pet();
        cat.setName("testCat");
        cat.setType(PetType.CAT);
        Pet dog = new Pet();
        dog.setName("testDog");
        dog.setType(PetType.DOG);

        repository.save(cat);
        repository.save(dog);

        assertEquals(2,repository.count());

        List<Pet> fetchedDogs = repository.findByType(PetType.DOG);
        assertEquals(1,fetchedDogs.size());
        assertEquals(fetchedDogs.get(0),dog);
    }
}
