package com.evandrosantos.cursomc.dto.produtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterPageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Integer> categorias = new ArrayList<>();
    private String nome;

    public FilterPageDTO() { }

    public FilterPageDTO(List<Integer> categorias, String nome) {
        setCategorias(categorias);
        setNome(nome);
    }

    public List<Integer> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Integer> categorias) {
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
