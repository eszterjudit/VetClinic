package com.me.vetclinic.controller;

import com.me.vetclinic.domain.LoginForm;
import com.me.vetclinic.domain.PetOwner;
import com.me.vetclinic.domain.Vet;
import com.me.vetclinic.service.PetOwnerService;
import com.me.vetclinic.service.VetService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@RestController
@Log4j
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class AuthController {

    @Autowired
    private PetOwnerService petOwnerService;

    @Autowired
    private VetService vetService;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    Map<String, Object> getToken(HttpSession session, Principal principal) {
        Map<String, Object> result = new HashMap<>();
        result.put("session", session.getId());
        if (petOwnerService.findByEmail(principal.getName()) != null) {
            result.put("id", petOwnerService.findByEmail(principal.getName()).getId());
            result.put("isVet", false);
        } else if(vetService.findByEmail(principal.getName()) != null) {
            result.put("id", vetService.findByEmail(principal.getName()).getId());
            result.put("isVet", true);
        }
        return result;
    }

    @RequestMapping(value = "/registerPetOwner", method = RequestMethod.POST)
    ResponseEntity<?> registerPetOwner(@RequestBody LoginForm loginForm) {
        List authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        inMemoryUserDetailsManager.createUser(new User(loginForm.getEmail(), loginForm.getPassword(), authorities));
        PetOwner petOwner = new PetOwner();
        petOwner.setEmail(loginForm.getEmail());
        petOwnerService.addPetOwner(petOwner);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registerVet", method = RequestMethod.POST)
    ResponseEntity<?> registerVet(@RequestBody LoginForm loginForm) {
        List authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        inMemoryUserDetailsManager.createUser(new User(loginForm.getEmail(), loginForm.getPassword(), authorities));
        Vet vet = new Vet();
        vet.setEmail(loginForm.getEmail());
        vetService.addVet(vet);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registerPetOwner", method = RequestMethod.POST)
    ResponseEntity<?> registerPetOwner(HttpServletRequest httpServletRequest, UriComponentsBuilder ucBuilder) {
        PetOwner petOwner = new PetOwner();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/petOwner/{petOwnerId}").buildAndExpand(petOwner.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
