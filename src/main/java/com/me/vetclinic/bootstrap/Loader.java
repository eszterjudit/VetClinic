package com.me.vetclinic.bootstrap;

import com.me.vetclinic.domain.*;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.repository.PetOwnerRepository;
import com.me.vetclinic.repository.PetRepository;
import com.me.vetclinic.repository.VetRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by totheszter on 2017. 09. 24..
 */
@Component
@Transactional
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = Logger.getLogger(Loader.class);

    @Autowired private PetRepository petRepository;
    @Autowired private PetOwnerRepository petOwnerRepository;
    @Autowired private VetRepository vetRepository;
    @Autowired private ClinicRepository clinicRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populatePetOwners();
        populateVets();
        try {
            populateClinics();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void populatePetOwners() {
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

    public void populateVets() {
        List specialities = new ArrayList();
        specialities.add(PetType.REPTILE);
        Vet vet = new Vet();
        vet.setFirstName("Vet");
        vet.setLastName("Vetson");
        vet.setEmail("reptile123@gmail.com");
        vet.setSpeciality(specialities);

        vetRepository.save(vet);
        log.info(vetRepository.findOne((long) 1));
    }

    public void populateClinics() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Clinic clinic = new Clinic();
        clinic.setName("First Clinic");
        clinic.setAddress("1234 Budapest Clinic st 1/A");
        clinic.setOpeningHour(format.parse("08:00"));
        clinic.setClosingHour(format.parse("14:00"));

        Clinic clinic2 = new Clinic();
        clinic2.setName("Second Clinic");
        clinic2.setAddress("1222 Budapest Clinic st 55");
        clinic2.setOpeningHour(format.parse("10:00"));
        clinic2.setClosingHour(format.parse("20:00"));

        clinicRepository.save(clinic);
        clinicRepository.save(clinic2);
    }
}
