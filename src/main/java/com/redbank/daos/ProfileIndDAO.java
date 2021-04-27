package com.redbank.daos;

import com.redbank.models.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileIndDAO extends JpaRepository<ProfileInd, String> {
    ProfileInd findByEmail(String email);
}
