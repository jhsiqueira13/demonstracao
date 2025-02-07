package com.jeriam.demonstracao.service;

import com.jeriam.demonstracao.model.dto.UsuarioLoginDto;
import com.jeriam.demonstracao.model.dto.UsuarioRegistroDto;
import com.jeriam.demonstracao.model.entity.Usuarios;
import java.time.LocalDate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jeriam.demonstracao.model.repository.UsuariosRepository;

/**
 *
 * @author jeriam
 */
@Service
public class AuthenticationService {

    private final UsuariosRepository UsuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UsuariosRepository UsuarioRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.UsuarioRepository = UsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuarios signup(UsuarioRegistroDto input) {
        Usuarios usuario = new Usuarios();
        usuario.setNome(input.getNome());
        usuario.setEmail(input.getEmail());
        usuario.setSenha(passwordEncoder.encode(input.getSenha()));
        usuario.setAtivo(Boolean.TRUE);
        usuario.setDataCriacao(LocalDate.now());
        return UsuarioRepository.save(usuario);
    }

    public Usuarios authenticate(UsuarioLoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getSenha()
                )
        );

        return UsuarioRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
