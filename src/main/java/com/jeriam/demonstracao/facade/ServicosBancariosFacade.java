package com.jeriam.demonstracao.facade;

import com.jeriam.demonstracao.exceptions.ClienteNaoEncontradosException;
import com.jeriam.demonstracao.exceptions.ServicosBancariosalidationException;
import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.MovimentacoesBancarias;
import com.jeriam.demonstracao.model.enums.CreditoDebitoEnum;
import com.jeriam.demonstracao.model.enums.MovimentacoesBancariasTipoEnum;
import com.jeriam.demonstracao.service.ClienteService;
import com.jeriam.demonstracao.service.EmprestimosService;
import com.jeriam.demonstracao.service.MovimentacoesBancariasService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jeriam
 */
@Service
public class ServicosBancariosFacade {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmprestimosService emprestimoService;

    @Autowired
    private MovimentacoesBancariasService movimentacoesBancariasService;

    public boolean solicitarEmprestimo(Long clienteId, double valor) {
        Clientes cliente = clienteService.buscar(clienteId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradosException("Cliente não encontrado"));

        return emprestimoService.avaliarCredito(cliente.getId(), valor)
                && emprestimoService.solicitarEmprestimo(cliente, valor)
                && movimentarConta(cliente.getId(), MovimentacoesBancariasTipoEnum.CREDITO_EMPRESTIMO, valor);
    }

    public boolean movimentarConta(Long clienteId, MovimentacoesBancariasTipoEnum tipo, double valor) {
        Clientes cliente = clienteService.buscar(clienteId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradosException("Cliente não encontrado"));
        if (tipo.getCreditoDebito().equals(CreditoDebitoEnum.DEBITO)) {
            if (cliente.getSaldo() < valor) {
                throw new ServicosBancariosalidationException("Sem Saldo Disponível");
            }
            cliente.setSaldo(cliente.getSaldo() - valor);
        } else {
            cliente.setSaldo(cliente.getSaldo() + valor);
        }
        return movimentacoesBancariasService.movimentar(cliente, tipo, valor);

    }

    public List<MovimentacoesBancarias> listar(Long clienteId) {
        Clientes cliente = clienteService.buscar(clienteId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradosException("Cliente não encontrado"));

        return movimentacoesBancariasService.listarMovimentacoes(cliente);
    }

    public String extrato(Long clienteId) {
        Clientes cliente = clienteService.buscar(clienteId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradosException("Cliente não encontrado"));

        StringBuilder extrato = new StringBuilder();
        movimentacoesBancariasService.listarMovimentacoes(cliente).stream().forEach(m -> {
            extrato.append(m.getDataHora().format(DateTimeFormatter.ISO_DATE_TIME)).append(" ")
                    .append(m.getTipo().name()).append(" ")
                    .append("R$ ").append(m.getValor()).append(" ")
                    .append("Saldo R$ ").append(m.getSaldoAtual())
                    .append("\n");
        });
        return extrato.toString();
    }

}
