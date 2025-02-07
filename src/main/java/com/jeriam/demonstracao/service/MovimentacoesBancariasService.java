package com.jeriam.demonstracao.service;

import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.MovimentacoesBancarias;
import com.jeriam.demonstracao.model.enums.MovimentacoesBancariasTipoEnum;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeriam.demonstracao.model.repository.MovimentacoesBancariasRepository;

/**
 *
 * @author jeriam
 */
@Service
public class MovimentacoesBancariasService {

    @Autowired
    private MovimentacoesBancariasRepository movimentacoesBancariasRepository;

    public boolean movimentar(Clientes cliente, MovimentacoesBancariasTipoEnum tipo, Double valor) {
        MovimentacoesBancarias movimentacao = new MovimentacoesBancarias();
        movimentacao.setClientes(cliente);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setTipo(tipo);
        movimentacao.setValor(valor);
        movimentacao.setSaldoAtual((cliente.getSaldo()));
        movimentacoesBancariasRepository.save(movimentacao);
        return true;
    }

    public List<MovimentacoesBancarias> listarMovimentacoes(Clientes cliente) {
        return movimentacoesBancariasRepository.findByClientesOrderByDataHoraDesc(cliente);
    }
    
}
