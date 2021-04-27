package com.redbank.controllers;

import com.redbank.dtos.requests.AuthRequestDTO;
import com.redbank.dtos.responses.AuthResponseDTO;
import com.redbank.services.impl.RedBankUserDetailsServiceImpl;
import com.redbank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String greeter(){

        return "Hello Springer";
    }
}
