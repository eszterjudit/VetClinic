package com.me.vetclinic.repository;

import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.domain.Vet;
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
public class VetRepositoryTest {
    @Autowired
    VetRepository repository;

    @Test
    public void testSaveVet() {
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();
        Vet vet3 = new Vet();

        List<PetType> specialities1 = new ArrayList<>();
        specialities1.add(PetType.CAT);
        specialities1.add(PetType.DOG);
        List<PetType> specialities2 = new ArrayList<>();
        specialities2.add(PetType.CAT);
        specialities2.add(PetType.REPTILE);

        vet1.setFirstName("vet1FirstName");
        vet2.setFirstName("vet2FirstName");
        vet3.setFirstName("vet3FirstName");

        vet1.setSpeciality(specialities1);
        vet2.setSpeciality(specialities1);
        vet3.setSpeciality(specialities2);

        repository.save(vet1);
        repository.save(vet2);
        repository.save(vet3);

        assertNotNull(vet1.getId());
        assertNotNull(vet2.getId());
        assertNotNull(vet3.getId());

        List<Vet> dogVets = repository.findBySpecialityContains(PetType.DOG);
        assertEquals(2,dogVets.size());

        assertTrue(dogVets.contains(vet1));
        assertTrue(dogVets.contains(vet2));
    }
}
