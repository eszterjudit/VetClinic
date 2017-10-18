package com.me.vetclinic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @RequestMapping(method = RequestMethod.GET)
    Map<String, Object> getToken(HttpSession session) {
        return Collections.singletonMap("session", session.getId());
    }
}
