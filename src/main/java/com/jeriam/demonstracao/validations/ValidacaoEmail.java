package com.jeriam.demonstracao.validations;

import java.util.regex.Pattern;

/**
 *
 * @author jeriam
 */
public class ValidacaoEmail {

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(REGEX_EMAIL);

    public static boolean validar(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
}
