package com.jeriam.demonstracao.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jeriam
 */
@Getter
@Setter
public class EmprestimoSolicitacaoDto {
    private Long clienteId;
    private Double valor;
}
