package com.jeriam.demonstracao.service;

import com.jeriam.demonstracao.exceptions.ClienteNaoEncontradosException;
import com.jeriam.demonstracao.exceptions.ClientesValidationException;
import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.ClientePessoaFisica;
import com.jeriam.demonstracao.model.entity.ClientePessoaJuridica;
import com.jeriam.demonstracao.model.filter.ClienteFiltro;
import com.jeriam.demonstracao.validations.ValidacaoCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeriam.demonstracao.model.repository.ClientesRepository;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Objects;

/**
 *
 * @author jeriam
 */
@Service
public class ClienteService {

    @Autowired
    private ClientesRepository repository;

    public List<Clientes> buscarTodos() {
        return repository.findAll();
    }

    public List<Clientes> buscar(Long clienteId) {
        if (clienteId == null) {
            throw new ClientesValidationException("Id do Cliente é obrigatório");
        }
        return repository.findById(clienteId)
                .map(List::of)
                .orElseThrow(() -> new ClienteNaoEncontradosException("Cliente não encontrado"));
    }

    public Integer contarClientes() {
        AtomicInteger total = new AtomicInteger(0);
        repository.findAll().stream().forEach(cliente -> total.incrementAndGet());
        return total.get();
    }

    public List<Clientes> filtrar(ClienteFiltro clienteFiltro) {
        return repository.filtro(clienteFiltro);
    }

    public ClientePessoaJuridica criarPJ(ClientePessoaJuridica cliente) {
        Objects.requireNonNull(cliente, "O cliente não pode ser nulo");
        ValidacaoCliente.validar(cliente);
        cliente.setId(null);
        cliente.setSaldo(Double.valueOf("0"));
        return repository.save(cliente);
    }

    public Clientes atualizarPJ(ClientePessoaJuridica cliente, Long clienteId) throws Exception {
        ValidacaoCliente.validar(cliente, clienteId);
        Clientes clientePersist;
        if (clienteId == null) {
            throw new ClienteNaoEncontradosException("Id não recebido");
        }
        Optional<Clientes> optCliente = repository.findById(clienteId);
        if (optCliente.isEmpty()) {
            throw new ClienteNaoEncontradosException("Cliente não encontrado");
        }
        if (!(optCliente.get() instanceof ClientePessoaJuridica)) {
            throw new ClientesValidationException("Cliente não é pessoa jurídica");
        }
        clientePersist = optCliente.get();
        BeanUtils.copyProperties(cliente, clientePersist, "id","saldo");
        return repository.save(clientePersist);
    }

    public Clientes criarPF(ClientePessoaFisica cliente) throws Exception {
        Objects.requireNonNull(cliente, "O cliente não pode ser nulo");
        ValidacaoCliente.validar(cliente);
        cliente.setId(null);
        cliente.setSaldo(Double.valueOf("0"));
        return repository.save(cliente);
    }

    public Clientes atualizarPF(ClientePessoaFisica cliente, Long clienteId) throws Exception {
        ValidacaoCliente.validar(cliente, clienteId);

        Clientes clientePersist;
        if (clienteId == null) {
            throw new ClienteNaoEncontradosException("Id não recebido");
        }
        Optional<Clientes> optCliente = repository.findById(clienteId);
        if (optCliente.isEmpty()) {
            throw new ClienteNaoEncontradosException("Cliente não encontrado");
        }
        if (!(optCliente.get() instanceof ClientePessoaFisica)) {
            throw new ClientesValidationException("Cliente não é pessoa física");
        }
        clientePersist = optCliente.get();
        BeanUtils.copyProperties(cliente, clientePersist, "id","saldo");
        return repository.save(clientePersist);
    }

    public void deletarPeloId(Long clienteId) {
        Clientes cliente = repository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradosException("Cliente não encontrado com ID: " + clienteId));
        repository.delete(cliente);
    }

    public Clientes save(Clientes cliente) {
        ValidacaoCliente.validar(cliente);
        return repository.save(cliente);
    }
}
