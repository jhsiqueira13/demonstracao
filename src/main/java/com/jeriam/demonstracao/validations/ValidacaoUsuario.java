package com.jeriam.demonstracao.validations;

import com.jeriam.demonstracao.model.entity.Usuarios;

/**
 *
 * @author jeriam
 */
import java.util.Objects;

public class ValidacaoUsuario {

    public static void validar(Usuarios usuario) {
        Objects.requireNonNull(usuario, "Usuário não pode ser nulo");

        validarNome(usuario.getNome());
        validarEmail(usuario.getEmail());
        validarSenha(usuario.getSenha());
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório.");
        }
    }

    private static void validarEmail(String email) {
        if (!ValidacaoEmail.validar(email)) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    private static void validarSenha(String senha) {
        if (senha == null || senha.isBlank() || senha.length() < 8) {
            throw new IllegalArgumentException("Senha inválida. Deve conter pelo menos 8 caracteres.");
        }
    }
}
