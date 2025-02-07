package com.jeriam.demonstracao.validations;

import com.jeriam.demonstracao.util.StringUtil;

/**
 *
 * @author jeriam
 */
public class ValidacaoCNPJ {

    public static boolean validar(String cnpj) {
        cnpj = StringUtil.somenteNumeros(cnpj);
        if (cnpj == null || cnpj.isBlank() || cnpj.length() != 14 || cnpj.matches("^0+$")) {
            return false;
        }

        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        String cnpjBase = cnpj.substring(0, 12);
        int digito1 = calcularDigito(cnpjBase, peso1);
        int digito2 = calcularDigito(cnpjBase + digito1, peso2);

        return cnpj.equals(cnpjBase + digito1 + digito2);
    }

    private static int calcularDigito(String base, int[] peso) {
        int soma = 0;
        for (int i = 0; i < base.length(); i++) {
            soma += (base.charAt(i) - '0') * peso[i];
        }
        int digito = 11 - (soma % 11);
        return (digito >= 10) ? 0 : digito;
    }
}
