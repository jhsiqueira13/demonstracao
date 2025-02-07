package com.jeriam.demonstracao.validations;

import com.jeriam.demonstracao.exceptions.ServicosBancariosalidationException;
import com.jeriam.demonstracao.model.entity.Emprestimos;

/**
 *
 * @author jeriam
 */
public class ValidacaoEmprestimo {

    public void validar(Emprestimos emprestimo) {
        if (emprestimo.getClientes() == null) {
            throw new ServicosBancariosalidationException("Cliente é obrigatório");
        }
        if (emprestimo.getEmprestimoSituacao() == null) {
            throw new ServicosBancariosalidationException("Situação do empréstimo é obrigatório");
        }
        if (emprestimo.getValor() == null || emprestimo.getValor().equals(Double.valueOf(0.00))) {
            throw new ServicosBancariosalidationException("Valor do empréstimo é obrigatório e deve ser maior que zero");
        }
    }
}
