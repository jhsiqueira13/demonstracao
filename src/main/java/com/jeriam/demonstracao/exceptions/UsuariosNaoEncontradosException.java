package com.jeriam.demonstracao.exceptions;

/**
 *
 * @author jeriam
 */
public class UsuariosNaoEncontradosException extends RuntimeException {

    // Construtor padrão
    public UsuariosNaoEncontradosException() {
        super();
    }

    // Construtor com mensagem de erro
    public UsuariosNaoEncontradosException(String message) {
        super(message);
    }

    // Construtor com mensagem de erro e causa
    public UsuariosNaoEncontradosException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com causa
    public UsuariosNaoEncontradosException(Throwable cause) {
        super(cause);
    }
}
