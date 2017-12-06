package com.me.vetclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.repository.ClinicRepository;
import com.me.vetclinic.service.VetService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
public class VetRestControllerTest {

    @InjectMocks
    VetRestController vetRestController;

    @Mock
    VetService vetService;

    @Mock
    ClinicRepository clinicRepository;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(vetRestController).build();
    }

    @Test
    public void testUpdateVet() throws Exception {
        Vet vet = new Vet();
        vet.setFirstName("vet first name");

        Vet updatedVet = new Vet();
        updatedVet.setFirstName("updated vet first name");

        when(vetService.findById((long) 1)).thenReturn(vet);

        mockMvc.perform(put("/vet/1")
                .content(asJsonString(updatedVet))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'updated vet first name'}"))
                .andReturn();

    }

    @Test
    public void testGetVetAllClinics() throws Exception {
        Vet vet = new Vet();
        Clinic clinic = new Clinic();
        clinic.setClinicName("clinic name");
        List<Clinic> clinicList = new ArrayList<>();
        clinicList.add(clinic);
        vet.setClinics(clinicList);

        when(clinicRepository.findByVets_Id((long) 1)).thenReturn(clinicList);

        mockMvc.perform(get("/vet/1/clinics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'clinicName':'clinic name'}]"))
                .andReturn();
    }

    @Test
    public void testGetVet() throws Exception {
        Vet vet = new Vet();
        vet.setFirstName("vet first name");

        when(vetService.findById((long) 1)).thenReturn(vet);

        mockMvc.perform(get("/vet/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'vet first name'}"))
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
