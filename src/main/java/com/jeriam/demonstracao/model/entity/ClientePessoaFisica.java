package com.jeriam.demonstracao.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author jeriam
 */
@Getter
@Setter
@Entity
@ToString
@DiscriminatorValue("pessoaFisica")
public class ClientePessoaFisica extends Clientes {

    private String cpf;
    private String nomeDaMae;
    private LocalDate dataNascimento;

}
