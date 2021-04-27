package com.redbank.controllers;

import com.redbank.dtos.requests.AuthRequestDTO;
import com.redbank.dtos.requests.ProfileBbDTO;
import com.redbank.dtos.requests.ProfileHosDTO;
import com.redbank.dtos.requests.ProfileIndDTO;
import com.redbank.dtos.responses.AuthResponseDTO;
import com.redbank.exceptions.CannotAddException;
import com.redbank.exceptions.ResourceAlreadyExistsException;
import com.redbank.models.ProfileBb;
import com.redbank.models.ProfileHos;
import com.redbank.models.ProfileInd;
import com.redbank.services.ProfileBbService;
import com.redbank.services.ProfileHosService;
import com.redbank.services.ProfileIndService;
import com.redbank.services.impl.RedBankUserDetailsServiceImpl;
import com.redbank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private RedBankUserDetailsServiceImpl redBankUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private ProfileIndService profileIndService;

    @Autowired
    private ProfileHosService profileHosService;

    @Autowired
    private ProfileBbService profileBbService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> logIn(@RequestBody @Valid AuthRequestDTO authRequestDTO) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        }
        //? IF AUTHENTICATION FAILS
        catch (BadCredentialsException e){
            throw new Exception("Invalid login credentials", e);
        }

        //? I NEED THE USER'S EMAIL, PASSWORD, TYPE AND ID HERE TO GENERATE THE TOKEN.
        Map<String, String> userDetails = redBankUserDetailsService.getUser(authRequestDTO.getEmail());

        final String token = jwtTokenUtil.generateUserToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponseDTO(token, userDetails.get("userId"), Integer.parseInt(userDetails.get("userType")), authTokenExpiry, refreshToken, refreshTokenExpiry));
    }

    @PostMapping("/register/ind")
    public ResponseEntity<AuthResponseDTO> registerIndividual(@RequestBody @Valid ProfileIndDTO profileIndDTO) throws Exception {

        //? CHECKING IF THE USER ALREADY EXISTS.

        ProfileInd profileInd = profileIndService.findByEmail(profileIndDTO.getEmail());
        ProfileHos profileHos = profileHosService.findByEmail(profileIndDTO.getEmail());
        ProfileBb profileBb = profileBbService.findByEmail(profileIndDTO.getEmail());

        if(profileInd != null || profileHos != null || profileBb != null){
            throw new ResourceAlreadyExistsException("An account already exists with this email. Please try again with a new email address.");
        }
        boolean status = profileIndService.createNewIndUser(profileIndDTO);
        if(!status) {
            throw new CannotAddException("Unable to add a new user at the moment. Please try again later.");
        }
        //? THE USER HAS BEEN ADDED TO DB, NOW TO GENERATE THE TOKEN.

        Map<String, String> userDetails = redBankUserDetailsService.getUser(profileIndDTO.getEmail());

        final String token = jwtTokenUtil.generateUserToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponseDTO(token, userDetails.get("userId"), Integer.parseInt(userDetails.get("userType")), authTokenExpiry, refreshToken, refreshTokenExpiry));

    }

    @PostMapping("/register/hos")
    public ResponseEntity<AuthResponseDTO> registerHospital(@Valid @RequestBody ProfileHosDTO profileHosDTO) throws Exception {

        //? CHECKING IF THE USER ALREADY EXISTS.
        ProfileInd profileInd = profileIndService.findByEmail(profileHosDTO.getEmail());
        ProfileHos profileHos = profileHosService.findByEmail(profileHosDTO.getEmail());
        ProfileBb profileBb = profileBbService.findByEmail(profileHosDTO.getEmail());

        if(profileInd != null || profileHos != null || profileBb != null){
            throw new ResourceAlreadyExistsException("An account already exists with this email. Please try again with a new email address.");
        }
        boolean status = profileHosService.createNewHosUser(profileHosDTO);
        if(!status) {
            throw new CannotAddException("Unable to add a new user at the moment. Please try again later.");
        }
        //? THE USER HAS BEEN ADDED TO DB, NOW TO GENERATE THE TOKEN.

        Map<String, String> userDetails = redBankUserDetailsService.getUser(profileHosDTO.getEmail());

        final String token = jwtTokenUtil.generateUserToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.ok().headers(responseHeaders).body(new AuthResponseDTO(token, userDetails.get("userId"), Integer.parseInt(userDetails.get("userType")), authTokenExpiry, refreshToken, refreshTokenExpiry));

    }

    @PostMapping("/register/bb")
    public ResponseEntity<AuthResponseDTO> registerBloodBank(@RequestBody @Valid ProfileBbDTO profileBbDTO) throws Exception {

        //? CHECKING IF THE USER ALREADY EXISTS.
        ProfileInd profileInd = profileIndService.findByEmail(profileBbDTO.getEmail());
        ProfileHos profileHos = profileHosService.findByEmail(profileBbDTO.getEmail());
        ProfileBb profileBb = profileBbService.findByEmail(profileBbDTO.getEmail());

        if(profileInd != null || profileHos != null || profileBb != null){
            throw new ResourceAlreadyExistsException("An account already exists with this email. Please try again with a new email address.");
        }
        boolean status = profileBbService.createNewBbUser(profileBbDTO);
        if(!status) {
            throw new CannotAddException("Unable to add a new user at the moment. Please try again later.");
        }
        //? THE USER HAS BEEN ADDED TO DB, NOW TO GENERATE THE TOKEN.

        Map<String, String> userDetails = redBankUserDetailsService.getUser(profileBbDTO.getEmail());

        final String token = jwtTokenUtil.generateUserToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date authTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails.get("userId"), userDetails.get("email"), Integer.parseInt(userDetails.get("userType")));
        final Date refreshTokenExpiry = jwtTokenUtil.getExpirationDateFromToken(token);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("success", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(new AuthResponseDTO(token, userDetails.get("userId"), Integer.parseInt(userDetails.get("userType")), authTokenExpiry, refreshToken, refreshTokenExpiry));

    }

}
