package com.redbank.services;

import com.redbank.dtos.requests.ProfileBbDTO;
import com.redbank.models.ProfileBb;

import java.text.ParseException;

public interface ProfileBbService {
    boolean createNewBbUser(ProfileBbDTO profileBbDTO) throws Exception;
    ProfileBb findByEmail(String email);
//    boolean updateIndUser(AuthResponseDTO authResponseDTO);
}
