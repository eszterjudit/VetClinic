package com.me.vetclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.service.PetOwnerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
public class PetOwnerRestControllerTest {

    @InjectMocks
    PetOwnerRestController petOwnerRestController;

    @Mock
    PetOwnerService petOwnerService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(petOwnerRestController).build();
    }

    @Test
    public void testAddPet() throws Exception {
        Pet pet = new Pet();
        pet.setPetName("pet name");
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("petowner first name");

        when(petOwnerService.findById((long) 1)).thenReturn(petOwner);

        mockMvc.perform(post("/petOwner/1/addPet")
                .content(asJsonString(pet))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testGetPetowner() throws Exception {
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("petowner first name");

        when(petOwnerService.findById((long) 1)).thenReturn(petOwner);

        mockMvc.perform(get("/petOwner/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'petowner first name'}"))
                .andReturn();
    }

    @Test
    public void testGetPetOwnerAllPets() throws Exception {
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("petowner first name");
        Pet pet = new Pet();
        pet.setPetName("pet name");
        List<Pet> petList = new ArrayList<>();
        petList.add(pet);
        petOwner.setPets(petList);

        when(petOwnerService.findById((long) 1)).thenReturn(petOwner);

        mockMvc.perform(get("/petOwner/1/pets")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'petName':'pet name'}]"))
                .andReturn();
    }

    @Test
    public void testUpdatePetOwner() throws Exception {
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("petowner first name");
        PetOwner updatedPetOwner = new PetOwner();
        updatedPetOwner.setFirstName("updated petowner first name");

        when(petOwnerService.findById((long) 1)).thenReturn(petOwner);

        mockMvc.perform(put("/petOwner/1")
                .content(asJsonString(updatedPetOwner))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'updated petowner first name'}"))
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}