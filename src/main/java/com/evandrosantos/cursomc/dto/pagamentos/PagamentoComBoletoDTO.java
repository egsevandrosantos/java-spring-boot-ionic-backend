package com.evandrosantos.cursomc.dto.pagamentos;

import com.evandrosantos.cursomc.domain.PagamentoComBoleto;
import com.evandrosantos.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PagamentoComBoletoDTO extends PagamentoDTO {
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPagamento;

    public PagamentoComBoletoDTO() { }

    public PagamentoComBoletoDTO(Integer id, EstadoPagamento estado, Date dataVencimento, Date dataPagamento) {
        super(id, estado);
        setDataVencimento(dataVencimento);
        setDataPagamento(dataPagamento);
    }

    public PagamentoComBoletoDTO(PagamentoComBoleto pagamento) {
        this(pagamento.getId(), pagamento.getEstado(), pagamento.getDataVencimento(), pagamento.getDataPagamento());
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
