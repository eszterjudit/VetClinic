package com.me.vetclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.vetclinic.configuration.RepositoryConfiguration;
import com.me.vetclinic.domain.Address;
import com.me.vetclinic.domain.Clinic;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.service.ClinicService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
public class ClinicRestControllerTest {

    @InjectMocks
    ClinicRestController clinicRestController;

    @Mock
    ClinicService clinicService;

    @Mock
    VetService vetService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(clinicRestController).build();
    }

    @Test
    public void testAddClinic() throws Exception {
        Vet vet = new Vet();

        when(vetService.findById((long) 1)).thenReturn(vet);

        mockMvc.perform(post("/clinic/1")
                .content(asJsonString(new Clinic()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testAddVetToClinic() throws Exception {
        Vet vet = new Vet();
        Clinic clinic = new Clinic();
        clinic.setClinicName("clinic");

        when(clinicService.findById((long) 1)).thenReturn(clinic);
        when(vetService.findById((long) 1)).thenReturn(vet);

        mockMvc.perform(post("/clinic/1/addVet")
                .content(asJsonString(1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'clinicName':'clinic'}"))
                .andReturn();
    }

    @Test
    public void testRemoveVetFromClinic() throws Exception {
        Vet vet = new Vet();
        Clinic clinic = new Clinic();
        clinic.setClinicName("clinic");

        when(clinicService.findById((long) 1)).thenReturn(clinic);
        when(vetService.findById((long) 1)).thenReturn(vet);

        mockMvc.perform(post("/clinic/1/removeVet")
                .content(asJsonString(1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'clinicName':'clinic'}"))
                .andReturn();
    }

    @Test
    public void testGetAllClinicsByCity() throws Exception {
        Clinic clinic = new Clinic();
        clinic.setClinicName("clinic");
        Address address = new Address();
        address.setCity("Budapest");
        clinic.setAddress(address);

        List<Clinic> clinicList = new ArrayList<>();
        clinicList.add(clinic);

        when(clinicService.findAll()).thenReturn(clinicList);

        mockMvc.perform(get("/clinic/")
                .param("city", "Budapest")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'clinicName':'clinic'}]"))
                .andReturn();

        mockMvc.perform(get("/clinic/")
                .param("city", "Miskolc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"))
                .andReturn();
    }

    @Test
    public void testGetAllClinics() throws Exception {
        Clinic clinic = new Clinic();
        clinic.setClinicName("clinic");

        List<Clinic> clinicList = new ArrayList<>();
        clinicList.add(clinic);

        when(clinicService.findAll()).thenReturn(clinicList);

        mockMvc.perform(get("/clinic/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'clinicName':'clinic'}]"))
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
