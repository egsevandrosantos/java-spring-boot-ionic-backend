package com.evandrosantos.cursomc.domain;

import com.evandrosantos.cursomc.domain.abstracts.Pagamento;
import com.evandrosantos.cursomc.domain.enums.EstadoPagamento;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoComCartaoDTO;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoDTO;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;
    private Integer numeroDeParcelas;

    public PagamentoComCartao() { super(); }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        setNumeroDeParcelas(numeroDeParcelas);
    }

    public PagamentoComCartao(EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        this(null, estado, pedido, numeroDeParcelas);
    }

    public PagamentoComCartao(PagamentoComCartaoDTO dto, Pedido pedido) {
        this(dto.getId(), dto.getEstado(), pedido, dto.getNumeroDeParcelas());
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
