package com.jeriam.demonstracao.model.dto;

import com.jeriam.demonstracao.model.enums.MovimentacoesBancariasTipoEnum;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jeriam
 */
@Getter
@Setter
public class MovimentacoesBancariasDto {
    private Long clienteId;
    private MovimentacoesBancariasTipoEnum tipo;
    private Double valor;
}
