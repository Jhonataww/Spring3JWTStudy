package com.example.springboot.dtos.security;

import com.example.springboot.models.security.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegisterDto (@NotBlank String login, @NotBlank String password, UserRole role) {

}
