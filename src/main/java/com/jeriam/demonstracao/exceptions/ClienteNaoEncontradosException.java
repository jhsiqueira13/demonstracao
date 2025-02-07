package com.jeriam.demonstracao.exceptions;

/**
 *
 * @author jeriam
 */
public class ClienteNaoEncontradosException extends RuntimeException {

    // Construtor sem parâmetros
    public ClienteNaoEncontradosException() {
        super();
    }

    // Construtor com uma mensagem personalizada
    public ClienteNaoEncontradosException(String message) {
        super(message);
    }

    // Construtor com mensagem e causa
    public ClienteNaoEncontradosException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com causa
    public ClienteNaoEncontradosException(Throwable cause) {
        super(cause);
    }
}
