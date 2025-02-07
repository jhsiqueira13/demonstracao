package com.jeriam.demonstracao.service;

import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.Emprestimos;
import com.jeriam.demonstracao.model.enums.EmprestimosSituacaoEnum;
import com.jeriam.demonstracao.model.repository.EmprestimosRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jeriam
 */
@Service
public class EmprestimosService {

    @Autowired
    private EmprestimosRepository repository;

    public boolean avaliarCredito(Long clienteId, double valor) {
        // aqui serão as regras de negócio
        // verifica se o cliente está qualificado para a quantidade solicitada
        // por exemplo consultar serasa
        // consultar restricoes internas etc...
        return true;
    }

    public boolean solicitarEmprestimo(Clientes cliente, Double valor) {
        Emprestimos emprestimos = new Emprestimos();
        emprestimos.setClientes(cliente);
        emprestimos.setDataHoraSolicitacao(LocalDateTime.now());
        emprestimos.setEmprestimoSituacao(EmprestimosSituacaoEnum.EM_ANALISE);
        emprestimos.setValor(valor);
        repository.save(emprestimos);
        return true;
    }
}
