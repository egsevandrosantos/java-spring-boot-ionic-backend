package com.evandrosantos.cursomc.dto.categorias;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.domain.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
    private String nome;
    private Status status;

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
        return this.status;
    }

    public String getDescricaoStatus() {
        return this.status.getDescricao();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
