package com.evandrosantos.cursomc.dto.pagamentos;

import com.evandrosantos.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
public abstract class PagamentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer estado;

    public PagamentoDTO() { }

    public PagamentoDTO(Integer id, EstadoPagamento estado) {
        setId(id);
        setEstado(estado);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoEstado() {
        return getEstado().getDescricao();
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.getEnumByCod(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }
}
