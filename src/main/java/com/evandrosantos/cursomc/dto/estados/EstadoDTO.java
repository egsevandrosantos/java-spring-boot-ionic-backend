package com.evandrosantos.cursomc.dto.estados;

import com.evandrosantos.cursomc.domain.Estado;

import java.io.Serializable;

public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    public EstadoDTO() { }

    public EstadoDTO(Integer id, String nome) {
        setId(id);
        setNome(nome);
    }

    public EstadoDTO(Estado estado) {
        this(estado.getId(), estado.getNome());
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
}
