package com.redbank.services;

import com.redbank.dtos.requests.ProfileIndDTO;
import com.redbank.models.ProfileInd;

import java.text.ParseException;

public interface ProfileIndService {
    boolean createNewIndUser(ProfileIndDTO profileIndDTO) throws Exception;
    ProfileInd findByEmail(String email);
//    boolean updateIndUser(AuthResponseDTO authResponseDTO);
}
