package com.jeriam.demonstracao.model.enums;

/**
 *
 * @author jeriam
 */
public enum MovimentacoesBancariasTipoEnum {
    PAGAMENTO_BOLETO(CreditoDebitoEnum.DEBITO), PAGAMENTO_PIX(CreditoDebitoEnum.DEBITO), COBRANCA_TAXAS(CreditoDebitoEnum.DEBITO),
    RECEBIMENTO_PIX(CreditoDebitoEnum.CREDITO), RECEBIMENTO_TED(CreditoDebitoEnum.CREDITO), CREDITO_EMPRESTIMO(CreditoDebitoEnum.CREDITO);

    private final CreditoDebitoEnum valor;

    MovimentacoesBancariasTipoEnum(CreditoDebitoEnum valorOpcao) {
        valor = valorOpcao;
    }

    public CreditoDebitoEnum getCreditoDebito() {
        return valor;
    }

}
