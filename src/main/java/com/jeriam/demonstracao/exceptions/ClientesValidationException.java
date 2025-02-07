package com.jeriam.demonstracao.exceptions;

/**
 *
 * @author jeriam
 */
public class ClientesValidationException extends RuntimeException {

    // Construtor sem parâmetros
    public ClientesValidationException() {
        super();
    }

    // Construtor com uma mensagem personalizada
    public ClientesValidationException(String message) {
        super(message);
    }

    // Construtor com mensagem e causa
    public ClientesValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com causa
    public ClientesValidationException(Throwable cause) {
        super(cause);
    }
}
