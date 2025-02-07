package com.jeriam.demonstracao.controller;

import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.ClientePessoaFisica;
import com.jeriam.demonstracao.model.entity.ClientePessoaJuridica;
import com.jeriam.demonstracao.model.filter.ClienteFiltro;
import com.jeriam.demonstracao.service.ClienteService;
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
@RequestMapping(path = "/api/v1/clientes")
public class ClientesController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(path = "/filtrar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<Clientes>> filtrar(@RequestBody ClienteFiltro filter) {
        return ResponseEntity.ok(clienteService.filtrar(filter));
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Clientes>> buscarTodos() {
        return new ResponseEntity<>(clienteService.buscarTodos(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{clienteId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Clientes>> buscar(@PathVariable long clienteId) {
        return new ResponseEntity<>(clienteService.buscar(clienteId), HttpStatus.OK);
    }

    @RequestMapping(path = "/contar", method = RequestMethod.GET)
    @ResponseBody
    @ApiResponse(description = "Apenas para demonstrar o uso do Atomic com stream")
    public ResponseEntity<Integer> contar() {
        return new ResponseEntity<>(clienteService.contarClientes(), HttpStatus.OK);
    }

    @RequestMapping(path = "/pessoaJuridica", method = RequestMethod.POST)
    public ResponseEntity<?> criarPJ(@RequestBody ClientePessoaJuridica cliente) {
        try {
            Clientes novoCliente = clienteService.criarPJ(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar Pessoa Jurídica: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/pessoaFisica", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> criarPF(@RequestBody ClientePessoaFisica cliente) {
        try {
            Clientes novoCliente = clienteService.criarPF(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar Pessoa Física: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/pessoaJuridica/{clienteId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> atualizarPJ(@RequestBody ClientePessoaJuridica cliente, @PathVariable long clienteId) {
        try {
            return new ResponseEntity<>(clienteService.atualizarPJ(cliente, clienteId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @RequestMapping(path = "/pessoaFisica/{clienteId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> atualizarPF(@RequestBody ClientePessoaFisica cliente, @PathVariable long clienteId) {
        try {
            return new ResponseEntity<>(clienteService.atualizarPF(cliente, clienteId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @RequestMapping(path = "/{clienteId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable long clienteId) {
        try {
            clienteService.deletarPeloId(clienteId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
