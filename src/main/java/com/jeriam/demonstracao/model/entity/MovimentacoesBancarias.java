package com.jeriam.demonstracao.model.entity;

import com.jeriam.demonstracao.model.enums.MovimentacoesBancariasTipoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jeriam
 */
@Setter
@Getter
@Entity
@Table(name = "movimentacoes_bancarias")
public class MovimentacoesBancarias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Clientes clientes;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private MovimentacoesBancariasTipoEnum tipo;

    private Double valor;

    private Double saldoAtual;

}
