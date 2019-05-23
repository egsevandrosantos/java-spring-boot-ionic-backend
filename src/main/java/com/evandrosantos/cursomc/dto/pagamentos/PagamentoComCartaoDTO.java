package com.evandrosantos.cursomc.dto.pagamentos;

import com.evandrosantos.cursomc.domain.PagamentoComCartao;
import com.evandrosantos.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartaoDTO extends PagamentoDTO {
    private static final long serialVersionUID = 1L;
    private Integer numeroDeParcelas;

    public PagamentoComCartaoDTO() { super(); }

    public PagamentoComCartaoDTO(Integer id, EstadoPagamento estado, Integer numeroDeParcelas) {
        super(id, estado);
        setNumeroDeParcelas(numeroDeParcelas);
    }

    public PagamentoComCartaoDTO(PagamentoComCartao pagamento) {
        this(pagamento.getId(), pagamento.getEstado(), pagamento.getNumeroDeParcelas());
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
