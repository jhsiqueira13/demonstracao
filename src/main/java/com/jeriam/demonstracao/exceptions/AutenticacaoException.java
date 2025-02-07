package com.jeriam.demonstracao.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author jeriam
 */
@RestControllerAdvice
public class AutenticacaoException {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail detalheErro = null;

        if (exception instanceof BadCredentialsException) {
            detalheErro = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            detalheErro.setProperty("description", "Email ou senha incorretos");

            return detalheErro;
        }

        if (exception instanceof AccountStatusException) {
            detalheErro = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalheErro.setProperty("description", "Conta bloqueada");
        }

        if (exception instanceof AccessDeniedException) {
            detalheErro = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalheErro.setProperty("description", "Você não está autorizado a acessar essa funcionalidade");
        }

        if (exception instanceof SignatureException) {
            detalheErro = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalheErro.setProperty("description", "Assinatura inválida");
        }

        if (exception instanceof ExpiredJwtException) {
            detalheErro = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            detalheErro.setProperty("description", "Token expirado");
        }

        if (detalheErro == null) {
            detalheErro = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            detalheErro.setProperty("description", "Server Error.");
        }

        return detalheErro;
    }
}
