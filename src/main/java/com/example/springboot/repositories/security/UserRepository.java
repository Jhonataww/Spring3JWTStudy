package com.example.springboot.repositories.security;

import com.example.springboot.models.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByLogin(String Login);

}
