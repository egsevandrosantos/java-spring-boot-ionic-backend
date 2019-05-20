package com.evandrosantos.cursomc.domain;

import com.evandrosantos.cursomc.domain.abstracts.Pagamento;
import com.evandrosantos.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;
    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoComBoleto() { super(); }

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        setDataVencimento(dataVencimento);
        setDataPagamento(dataPagamento);
    }

    public PagamentoComBoleto(EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        this(null, estado, pedido, dataVencimento, dataPagamento);
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
