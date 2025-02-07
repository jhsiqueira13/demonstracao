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
@DiscriminatorValue("pessoaJuridica")
public class ClientePessoaJuridica extends Clientes {

    private String cnpj;
    private String nomeFantasia;
    private LocalDate dataFundacao;
    
}
