package com.evandrosantos.cursomc.dto.cidades;

import com.evandrosantos.cursomc.domain.Cidade;
import com.evandrosantos.cursomc.dto.estados.EstadoDTO;

import java.io.Serializable;

public class CidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private EstadoDTO estado;

    public CidadeDTO() { }

    public CidadeDTO(Integer id, String nome, EstadoDTO estado) {
        setId(id);
        setNome(nome);
        setEstado(estado);
    }

    public CidadeDTO(Cidade cidade) {
        this(cidade.getId(), cidade.getNome(), new EstadoDTO(cidade.getEstado()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }
}
