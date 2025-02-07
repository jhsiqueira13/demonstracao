package com.jeriam.demonstracao.exceptions;

/**
 *
 * @author jeriam
 */
public class ServicosBancariosalidationException extends RuntimeException {

    // Construtor sem parâmetros
    public ServicosBancariosalidationException() {
        super();
    }

    // Construtor com uma mensagem personalizada
    public ServicosBancariosalidationException(String message) {
        super(message);
    }

    // Construtor com mensagem e causa
    public ServicosBancariosalidationException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor com causa
    public ServicosBancariosalidationException(Throwable cause) {
        super(cause);
    }
}
