package com.evandrosantos.cursomc.dto.categorias;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.domain.enums.Status;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private Integer status;

    public CategoriaDTO() { }

    public CategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getStatus());
    }

    public CategoriaDTO(Integer id, String nome, Status status) {
        setId(id);
        setNome(nome);
        setStatus(status);
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

    public Status getStatus() {
        return Status.getEnumByCod(this.status);
    }

    public String getDescricaoStatus() {
        return this.getStatus().getDescricao();
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }
}
