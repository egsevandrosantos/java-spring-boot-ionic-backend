package com.evandrosantos.cursomc.dto.produtos;

import com.evandrosantos.cursomc.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() { }

    public ProdutoDTO(Integer id, String nome, Double preco) {
        setId(id);
        setNome(nome);
        setPreco(preco);
    }

    public ProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco());
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
