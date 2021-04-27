package com.redbank.services.impl;

import com.redbank.daos.ProfileHosDAO;
import com.redbank.dtos.requests.ProfileHosDTO;
import com.redbank.models.ProfileHos;
import com.redbank.models.ProfileInd;
import com.redbank.services.ProfileHosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ProfileHosServiceImpl implements ProfileHosService {

    @Autowired
    private ProfileHosDAO profileHosDAO;

    @Override
    public boolean createNewHosUser(ProfileHosDTO profileHosDTO) throws Exception {

        int phoneListSize = profileHosDTO.getPhone().size();

        ProfileHos profileHos = new ProfileHos(
                profileHosDTO.getName(),
                profileHosDTO.getEmail(),
                profileHosDTO.getLicenseNumber(),
                profileHosDTO.getPhone().get(0),
                phoneListSize >=2 ? profileHosDTO.getPhone().get(1) : null,
                phoneListSize >=3 ? profileHosDTO.getPhone().get(2) : null,
                phoneListSize >=4 ? profileHosDTO.getPhone().get(3) : null,
                phoneListSize >=5 ? profileHosDTO.getPhone().get(4) : null,
                profileHosDTO.getAddress(),
                profileHosDTO.getState(),
                profileHosDTO.getDistrict(),
                profileHosDTO.getPincode(),
                profileHosDTO.getPassword(),
                new Timestamp(System.currentTimeMillis())
        );
        profileHosDAO.save(profileHos);
        return true;
    }

    @Override
    public ProfileHos findByEmail(String email) {
        return profileHosDAO.findByEmail(email);
    }
}
