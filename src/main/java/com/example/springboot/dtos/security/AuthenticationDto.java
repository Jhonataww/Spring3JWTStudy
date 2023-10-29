package com.example.springboot.dtos.security;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(@NotBlank String login, @NotBlank String password) {
}
