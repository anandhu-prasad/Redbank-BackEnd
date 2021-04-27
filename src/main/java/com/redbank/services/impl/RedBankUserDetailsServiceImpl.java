package com.redbank.services.impl;


import com.redbank.daos.ProfileBbDAO;
import com.redbank.daos.ProfileHosDAO;
import com.redbank.daos.ProfileIndDAO;
import com.redbank.models.ProfileBb;
import com.redbank.models.ProfileHos;
import com.redbank.models.ProfileInd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class RedBankUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ProfileIndDAO profileIndDAO;

    @Autowired
    ProfileHosDAO profileHosDAO;

    @Autowired
    ProfileBbDAO profileBbDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //? HERE WE USE THE UNIQUE EMAIL TO LOCATE THE USER FROM THE THREE TABLES.

        //? AT MOST ONE OF THESE WILL HAVE A VALUE
        ProfileInd profileInd = profileIndDAO.findByEmail(email);
        ProfileHos profileHos = profileHosDAO.findByEmail(email);
        ProfileBb profileBb = profileBbDAO.findByEmail(email);

        //? IF ALL 3 ARE null
        if (profileInd == null && profileHos == null && profileBb == null ) {
            throw new UsernameNotFoundException("No account exists with " + email);
        }
        if(profileInd != null){
            return new User(email, profileInd.getPassword(), new ArrayList<>());
        }
        else if(profileHos != null){
            return new User(email, profileHos.getPassword(), new ArrayList<>());
        }
        else{
            return new User(email, profileBb.getPassword(), new ArrayList<>());
        }
    }


    public Map<String, String> getUser(String email) throws UsernameNotFoundException {
        //? HERE WE USE THE UNIQUE EMAIL TO LOCATE THE USER FROM THE THREE TABLES.

        //? AT MOST ONE OF THESE WILL HAVE A VALUE
        ProfileInd profileInd = profileIndDAO.findByEmail(email);
        ProfileHos profileHos = profileHosDAO.findByEmail(email);
        ProfileBb profileBb = profileBbDAO.findByEmail(email);

        //? IF ALL 3 ARE null
        if (profileInd == null && profileHos == null && profileBb == null ) {
            throw new UsernameNotFoundException("No account exists with " + email);
        }

        Map<String, String> userData = new HashMap<>();

        if(profileInd != null){
            userData.put("email",profileInd.getEmail());
            userData.put("password",profileInd.getPassword());
            userData.put("userType","1");
            userData.put("userId",profileInd.getUserId());
        }
        else if(profileHos != null){
            userData.put("email",profileHos.getEmail());
            userData.put("password",profileHos.getPassword());
            userData.put("userType","1");
            userData.put("userId",profileHos.getUserId());
        }
        else{
            userData.put("email",profileBb.getEmail());
            userData.put("password",profileBb.getPassword());
            userData.put("userType","1");
            userData.put("userId",profileBb.getUserId());
        }
        return userData;
    }

}
