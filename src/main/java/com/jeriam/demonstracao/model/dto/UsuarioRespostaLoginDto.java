package com.jeriam.demonstracao.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jeriam
 */
@Getter
@Setter
public class UsuarioRespostaLoginDto {

    private String token;

    private long expiresIn;

}
