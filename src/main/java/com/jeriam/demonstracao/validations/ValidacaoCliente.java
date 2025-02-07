package com.jeriam.demonstracao.validations;

import com.jeriam.demonstracao.exceptions.ClientesValidationException;
import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.ClientePessoaFisica;
import com.jeriam.demonstracao.model.entity.ClientePessoaJuridica;

/**
 *
 * @author jeriam
 */
public class ValidacaoCliente {

    public static void validar(Clientes cliente) throws ClientesValidationException {
        validar(cliente, null);
    }

    public static void validar(Clientes cliente, Long clienteId) throws ClientesValidationException {
        if (clienteId != null && cliente.getId() != null && !cliente.getId().equals(clienteId)) {
            throw new ClientesValidationException("Id do cliente no corpo da requisição é diferente do path");
        }
        validarCamposObrigatorios(cliente);
        if (cliente instanceof ClientePessoaFisica) {
            validarPessoaFisica((ClientePessoaFisica) cliente);
        } else if (cliente instanceof ClientePessoaJuridica) {
            validarPessoaJuridica((ClientePessoaJuridica) cliente);
        }
    }

    private static void validarCamposObrigatorios(Clientes cliente) throws ClientesValidationException {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new ClientesValidationException("Nome é obrigatório");
        }
        if (cliente.getLogradouro() == null || cliente.getLogradouro().isBlank()) {
            throw new ClientesValidationException("Logradouro é obrigatório");
        }
        if (cliente.getNumero() == null || cliente.getNumero().isBlank()) {
            throw new ClientesValidationException("Número do endereço é obrigatório");
        }
        if (cliente.getCidade() == null || cliente.getCidade().isBlank()) {
            throw new ClientesValidationException("Cidade é obrigatória");
        }
        if (cliente.getEstado() == null || cliente.getEstado().isBlank()) {
            throw new ClientesValidationException("Estado é obrigatório");
        }
    }

    private static void validarPessoaFisica(ClientePessoaFisica pessoaFisica) throws ClientesValidationException {
        if (!ValidacaoCPF.validar(pessoaFisica.getCpf())) {
            throw new ClientesValidationException("CPF está inválido");
        }
        if (pessoaFisica.getDataNascimento() == null) {
            throw new ClientesValidationException("Data de nascimento é obrigatória");
        }
        if (pessoaFisica.getNomeDaMae() == null || pessoaFisica.getNomeDaMae().isBlank()) {
            throw new ClientesValidationException("Nome da mãe é obrigatório");
        }
    }

    private static void validarPessoaJuridica(ClientePessoaJuridica pessoaJuridica) throws ClientesValidationException {
        if (!ValidacaoCNPJ.validar(pessoaJuridica.getCnpj())) {
            throw new ClientesValidationException("CNPJ está inválido");
        }
        if (pessoaJuridica.getDataFundacao() == null) {
            throw new ClientesValidationException("Data de fundação é obrigatória");
        }
    }
}
