package com.me.vetclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Pet;
import com.me.vetclinic.service.PetService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
public class PetRestControllerTest {

    @InjectMocks
    PetRestController petRestController;

    @Mock
    PetService petService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(petRestController).build();
    }

    @Test
    public void testDeletePet() throws Exception {
        Pet pet = new Pet();

        when(petService.findById((long) 1)).thenReturn(pet);

        mockMvc.perform(delete("/pet/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void testUpdatePet() throws Exception {
        Pet pet = new Pet();
        pet.setPetName("pet");

        Pet updatedPet = new Pet();
        updatedPet.setPetName("updated pet");

        when(petService.findById((long) 1)).thenReturn(pet);

        mockMvc.perform(put("/pet/1")
                .content(asJsonString(updatedPet))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'petName':'updated pet'}"))
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
