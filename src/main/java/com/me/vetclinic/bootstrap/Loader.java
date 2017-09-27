package com.me.vetclinic.bootstrap;

import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.domain.PetType;
import com.me.vetclinic.repository.PetOwnerRepository;
import com.me.vetclinic.repository.PetRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totheszter on 2017. 09. 24..
 */
@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = Logger.getLogger(Loader.class);

    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populatePetOwners();
    }

    public void populatePetOwners(){
        Pet pet = new Pet();
        pet.setName("Bruno");
        pet.setType(PetType.CAT);
        pet.setWeight(3.1);
        pet.setDateOfBirth(LocalDate.of(2007, 8, 1));
        petRepository.save(pet);
        log.info(petRepository.findOne((long) 1));
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);

        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("Jack");
        petOwner.setLastName("Jonson");
        petOwner.setEmail("jack.jonson@email.com");
        petOwner.setPets(pets);
        log.info(petOwner);
        log.info(petOwnerRepository.save(petOwner));
        log.info(petOwnerRepository.findOne((long) 1));

        log.info("Pet owner population finished");
        petOwnerRepository.findAll().stream().forEach(p -> log.info(p + " added"));
    }
}
