package com.jeriam.demonstracao.controller;

import com.jeriam.demonstracao.model.dto.UsuarioLoginDto;
import com.jeriam.demonstracao.model.dto.UsuarioRegistroDto;
import com.jeriam.demonstracao.model.dto.UsuarioRespostaLoginDto;
import com.jeriam.demonstracao.model.entity.Usuarios;
import com.jeriam.demonstracao.service.AuthenticationService;
import com.jeriam.demonstracao.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jeriam
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuarios> register(@RequestBody UsuarioRegistroDto usuarioRegistroDto) {
        Usuarios registeredUser = authenticationService.signup(usuarioRegistroDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioRespostaLoginDto> authenticate(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        Usuarios authenticatedUser = authenticationService.authenticate(usuarioLoginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        UsuarioRespostaLoginDto resposta = new UsuarioRespostaLoginDto();
        resposta.setToken(jwtToken);
        resposta.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(resposta);
    }

}
