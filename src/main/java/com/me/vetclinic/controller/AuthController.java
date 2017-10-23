package com.me.vetclinic.controller;

import com.me.vetclinic.service.PetOwnerService;
import com.me.vetclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private PetOwnerService petOwnerService;

    @Autowired
    private VetService vetService;

    @RequestMapping(method = RequestMethod.GET)
    Map<String, Object> getToken(HttpSession session, Principal principal) {
        Map<String, Object> result = new HashMap<>();
        result.put("session", session.getId());
        if (petOwnerService.findByEmail(principal.getName()).isPresent()) {
            result.put("id", petOwnerService.findByEmail(principal.getName()).get().getId());
            result.put("isVet", false);
        } else if(vetService.findByEmail(principal.getName()).isPresent()) {
            result.put("id", vetService.findByEmail(principal.getName()).get().getId());
            result.put("isVet", true);
        }
        return result;
    }
}
