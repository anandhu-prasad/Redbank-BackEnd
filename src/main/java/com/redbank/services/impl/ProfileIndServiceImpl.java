package com.redbank.services.impl;

import com.redbank.daos.ProfileIndDAO;
import com.redbank.dtos.requests.AuthRequestDTO;
import com.redbank.dtos.requests.ProfileIndDTO;
import com.redbank.dtos.responses.AuthResponseDTO;
import com.redbank.models.ProfileInd;
import com.redbank.services.ProfileIndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ProfileIndServiceImpl implements ProfileIndService {

    @Autowired
    private ProfileIndDAO profileIndDAO;

    @Override
    public boolean createNewIndUser(ProfileIndDTO profileIndDTO) throws Exception {
        ProfileInd profileInd = new ProfileInd(
                profileIndDTO.getName(),
                profileIndDTO.getBloodGroup(),
                profileIndDTO.getEmail(),
                new SimpleDateFormat("dd/MM/yy").parse(profileIndDTO.getDob()),
                profileIndDTO.getPhone(),
                profileIndDTO.getAddress(),
                profileIndDTO.getState(),
                profileIndDTO.getDistrict(),
                profileIndDTO.getPincode(),
                new Timestamp(System.currentTimeMillis()),
                0,
                profileIndDTO.getPassword()
        );
        profileIndDAO.save(profileInd);
        return true;
    }

    @Override
    public ProfileInd findByEmail(String email) {
        return profileIndDAO.findByEmail(email);
    }
}
