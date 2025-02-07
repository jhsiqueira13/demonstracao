package com.jeriam.demonstracao.validations;

import com.jeriam.demonstracao.util.StringUtil;

/**
 *
 * @author jeriam
 */
public class ValidacaoCPF {

    public static boolean validar(String cpf) {
        cpf = StringUtil.somenteNumeros(cpf);
        if (cpf == null || cpf.isBlank() || cpf.length() != 11 || cpf.matches("^0+$")) {
            return false;
        }

        int[] peso1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        String cpfBase = cpf.substring(0, 9);
        int digito1 = calcularDigito(cpfBase, peso1);
        int digito2 = calcularDigito(cpfBase + digito1, peso2);

        return cpf.equals(cpfBase + digito1 + digito2);
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
