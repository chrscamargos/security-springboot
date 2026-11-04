package com.biolab.security.controller;

import com.biolab.security.dto.LoginDTO;
import com.biolab.security.entities.Usuario;
import com.biolab.security.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// nome correto AuthController
@RestController
public class Login {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;

    public Login(CustomUserDetailsService customUserDetailsService, PasswordEncoder encoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.encoder = encoder;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto){
        try {
            var u = customUserDetailsService.loadUserByUsername(dto.getCpf());
            if (!encoder.matches(dto.getSenha(), u.getPassword())){
                return ResponseEntity.ok("Usuário ou Senha incorretos.");
            }
            return ResponseEntity.ok("Logado com sucesso.");

        } catch (UsernameNotFoundException e){
            return ResponseEntity.ok("Usuário não encontrado.");
        }

    }
}
