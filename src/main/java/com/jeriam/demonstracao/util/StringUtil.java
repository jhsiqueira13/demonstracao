package com.jeriam.demonstracao.util;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author jeriam
 */
public class StringUtil {

    private static final Pattern NON_DIGIT_PATTERN = Pattern.compile("\\D+");
    private static final Pattern NON_LETTERS_PATTERN = Pattern.compile("[^\\pL]+");

    /**
     * Retorna uma string contendo somente números da string fornecida.
     *
     * @param str a string a ser filtrada.
     * @return a string contendo apenas números ou uma string vazia se for nula.
     */
    public static String somenteNumeros(String str) {
        return NON_DIGIT_PATTERN.matcher(Objects.requireNonNullElse(str, "")).replaceAll("");
    }

    /**
     * Remove todos os caracteres não alfabéticos da string fornecida.
     *
     * @param str a string a ser filtrada.
     * @return uma string contendo apenas letras ou uma string vazia se a
     * entrada for nula.
     */
    public static String somenteLetras(String str) {
        return NON_LETTERS_PATTERN.matcher(Objects.requireNonNullElse(str, "")).replaceAll("");
    }

}
