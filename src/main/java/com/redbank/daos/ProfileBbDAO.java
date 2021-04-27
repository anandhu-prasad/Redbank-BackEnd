package com.redbank.daos;

import com.redbank.models.ProfileBb;
import com.redbank.models.ProfileInd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileBbDAO extends JpaRepository<ProfileBb, String> {
    ProfileBb findByEmail(String email);
}
