package com.jeriam.demonstracao.controller;

import com.jeriam.demonstracao.facade.ServicosBancariosFacade;
import com.jeriam.demonstracao.model.dto.EmprestimoSolicitacaoDto;
import com.jeriam.demonstracao.model.dto.MovimentacoesBancariasDto;
import com.jeriam.demonstracao.model.entity.MovimentacoesBancarias;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jeriam
 */
@RestController
@RequestMapping(path = "/api/v1/movimentacoesBancarias")
@ApiResponse(description = "Demonsração do uso da camada Facade")
public class MovimentacoesBancariasControler {

    @Autowired
    private ServicosBancariosFacade servicosBancarios;

    @RequestMapping(path = "/emprestimos/solicitar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> solicitarEmprestimo(@RequestBody EmprestimoSolicitacaoDto emprestimo) {
        try {
            boolean emprestimoConcedido = servicosBancarios.solicitarEmprestimo(
                    emprestimo.getClienteId(), emprestimo.getValor()
            );

            return emprestimoConcedido
                    ? ResponseEntity.ok("Empréstimo concedido")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empréstimo recusado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar solicitação: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/{clienteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> listar(@PathVariable Long clienteId) {
        try {
            List<MovimentacoesBancarias> movimentacoes = servicosBancarios.listar(clienteId);
            return ResponseEntity.ok(movimentacoes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar solicitação: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/movimentar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> movimentar(@RequestBody MovimentacoesBancariasDto movimentacao) {
        try {
            boolean movimentacaoEfetivada = servicosBancarios.movimentarConta(
                    movimentacao.getClienteId(),
                    movimentacao.getTipo(),
                    movimentacao.getValor()
            );

            return movimentacaoEfetivada
                    ? ResponseEntity.ok("Movimentação concluída")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao Movimentar");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar solicitação: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/extrato/{clienteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> extrato(@PathVariable Long clienteId) {
        try {
            String extrato = servicosBancarios.extrato(clienteId);
            return ResponseEntity.ok(extrato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar solicitação: " + e.getMessage());
        }
    }

}
