package com.redbank.daos;

import com.redbank.models.ProfileHos;
import com.redbank.models.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileHosDAO extends JpaRepository<ProfileHos, String> {
    ProfileHos findByEmail(String email);
}
