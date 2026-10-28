package com.biolab.security.controller;

import com.biolab.security.dto.UsuarioDTO;
import com.biolab.security.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO dto){
        return ResponseEntity.ok(usuarioService.saveUser(dto));
    }
}
