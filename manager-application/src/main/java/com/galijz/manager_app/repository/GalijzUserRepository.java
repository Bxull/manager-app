package com.galijz.manager_app.repository;

import com.galijz.manager_app.entity.GalijzUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalijzUserRepository extends JpaRepository<GalijzUser, Long> {

    Optional<GalijzUser> findByUsername(String username);
}
