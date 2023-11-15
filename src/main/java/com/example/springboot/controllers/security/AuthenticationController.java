package com.example.springboot.controllers.security;

import com.example.springboot.dtos.security.AuthenticationDto;
import com.example.springboot.dtos.security.LoginResponseDto;
import com.example.springboot.dtos.security.RegisterDto;
import com.example.springboot.models.security.User;
import com.example.springboot.repositories.security.UserRepository;
import com.example.springboot.services.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamepassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamepassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());

        //return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto data){
        if(this.userRepository.findByLogin(data.login()) != null ) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User novoUser = new User(data.login(), encryptedPassword, data.role());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userRepository.save(novoUser));
    }
}
