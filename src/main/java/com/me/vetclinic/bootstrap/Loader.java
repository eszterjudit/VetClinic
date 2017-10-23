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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = Logger.getLogger(Loader.class);

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;
    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populatePetOwners();
        populateVets();
    }

    public void populatePetOwners() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, 8, 1);
        Pet pet = new Pet();
        pet.setName("Bruno");
        pet.setType(PetType.CAT);
        pet.setWeight(3.1);
        pet.setDateOfBirth(calendar.getTime());
        petRepository.save(pet);
        log.info(petRepository.findOne((long) 1));
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);

        Address address = new Address();
        address.setStreet("sdhagd 2");

        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("Jack");
        petOwner.setLastName("Jonson");
        petOwner.setEmail("jack.jonson@email.com");
        petOwner.setPhone("1234567");
        petOwner.setAddress(address);

        petOwner.setPets(pets);
        log.info(petOwnerRepository.save(petOwner));

        log.info("Pet owner population finished");
        petOwnerRepository.findAll().stream().forEach(p -> log.info(p + " added"));
    }

    public void populateVets() {
        Address address = new Address();
        address.setCity("Budapest");
        address.setCountry("Hungary");
        address.setStreet("Németvölgyi út 20");
        address.setZip("1126");

        Address address2 = new Address();
        address2.setCity("Budapest");
        address2.setCountry("Hungary");
        address2.setStreet("dsdd 2");
        address2.setZip("1234");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 14);
        calendar2.set(Calendar.MINUTE, 40);

        Clinic clinic = new Clinic();
        clinic.setName("First Clinic");
        clinic.setAddress(address);
        clinic.setOpeningHour(calendar.getTime());
        clinic.setClosingHour(calendar2.getTime());

        Clinic clinic2 = new Clinic();
        clinic2.setName("Second Clinic");
        clinic2.setAddress(address2);
        clinic2.setOpeningHour(calendar.getTime());
        clinic2.setClosingHour(calendar2.getTime());

        List<Clinic> clinics = new ArrayList<>();
        clinics.add(clinic);
        clinics.add(clinic2);

        List specialities = new ArrayList();
        specialities.add(PetType.REPTILE);

        List specialities2 = new ArrayList();
        specialities.add(PetType.REPTILE);
        specialities.add(PetType.CAT);

        Vet vet = new Vet();
        vet.setFirstName("Vet");
        vet.setLastName("Vetson");
        vet.setEmail("reptile123@gmail.com");
        vet.setSpeciality(specialities);
        vet.setClinics(clinics);

        Vet vet2 = new Vet();
        vet2.setFirstName("New");
        vet2.setLastName("Vet");
        vet2.setEmail("newvet@gmail.com");
        vet2.setSpeciality(specialities2);
        vet2.setClinics(clinics);

        Vet vet3 = new Vet();
        vet3.setFirstName("Third");
        vet3.setLastName("Vet");
        vet3.setEmail("vetvet@gmail.com");
        vet3.setSpeciality(specialities);
        vet3.setClinics(clinics);

        clinicRepository.save(clinic);
        clinicRepository.save(clinic2);
        vetRepository.save(vet);
        vetRepository.save(vet2);
        vetRepository.save(vet3);

        log.info(vetRepository.findOne((long) 1));
    }
}
