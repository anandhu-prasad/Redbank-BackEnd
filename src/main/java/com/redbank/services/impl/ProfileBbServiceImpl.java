package com.redbank.services.impl;

import com.redbank.daos.ProfileBbDAO;
import com.redbank.daos.ProfileIndDAO;
import com.redbank.dtos.requests.ProfileBbDTO;
import com.redbank.models.ProfileBb;
import com.redbank.models.ProfileHos;
import com.redbank.services.ProfileBbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;

@Service
public class ProfileBbServiceImpl implements ProfileBbService {

    @Autowired
    private ProfileBbDAO profileBbDAO;

    @Override
    public boolean createNewBbUser(ProfileBbDTO profileBbDTO) throws Exception {
        int phoneListSize = profileBbDTO.getPhone().size();

        ProfileBb profileBb = new ProfileBb(
                profileBbDTO.getName(),
                profileBbDTO.getEmail(),
                profileBbDTO.getLicenseNumber(),
                profileBbDTO.getPhone().get(0),
                phoneListSize >=2 ? profileBbDTO.getPhone().get(1) : null,
                phoneListSize >=3 ? profileBbDTO.getPhone().get(2) : null,
                phoneListSize >=4 ? profileBbDTO.getPhone().get(3) : null,
                phoneListSize >=5 ? profileBbDTO.getPhone().get(4) : null,
                profileBbDTO.getAddress(),
                profileBbDTO.getState(),
                profileBbDTO.getDistrict(),
                profileBbDTO.getPincode(),
                profileBbDTO.getPassword(),
                new Timestamp(System.currentTimeMillis())
        );
        profileBbDAO.save(profileBb);
        return true;
    }

    @Override
    public ProfileBb findByEmail(String email) {
        return profileBbDAO.findByEmail(email);
    }
}
