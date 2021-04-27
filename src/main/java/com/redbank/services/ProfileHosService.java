package com.redbank.services;

import com.redbank.dtos.requests.ProfileHosDTO;
import com.redbank.dtos.requests.ProfileIndDTO;
import com.redbank.models.ProfileHos;
import com.redbank.models.ProfileInd;

import java.text.ParseException;

public interface ProfileHosService {
    boolean createNewHosUser(ProfileHosDTO profileHosDTO) throws Exception;
    ProfileHos findByEmail(String email);
//    boolean updateIndUser(AuthResponseDTO authResponseDTO);
}
