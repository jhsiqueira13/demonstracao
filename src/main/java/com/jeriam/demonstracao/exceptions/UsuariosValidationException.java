package com.jeriam.demonstracao.exceptions;

/**
 *
 * @author jeriam
 */
public class UsuariosValidationException extends RuntimeException {

    // Construtor sem parâmetros
    public UsuariosValidationException() {
        super();
    }

    // Construtor com uma mensagem personalizada
    public UsuariosValidationException(String message) {
        super(message);
    }

    // Construtor com mensagem e causa
    public UsuariosValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com causa
    public UsuariosValidationException(Throwable cause) {
        super(cause);
    }
}
