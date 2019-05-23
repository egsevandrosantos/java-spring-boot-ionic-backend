package com.evandrosantos.cursomc.dto.itensPedidos;

import com.evandrosantos.cursomc.domain.ItemPedido;
import com.evandrosantos.cursomc.dto.pedidos.PedidoDTO;
import com.evandrosantos.cursomc.dto.produtos.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ItemPedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private ItemPedidoPKDTO id;
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedidoDTO() { }

    public ItemPedidoDTO(ItemPedidoPKDTO id, Double desconto, Integer quantidade, Double preco) {
        setId(id);
        setDesconto(desconto);
        setQuantidade(quantidade);
        setPreco(preco);
    }

    public ItemPedidoDTO(ItemPedido itemPedido) {
        this(null, itemPedido.getDesconto(), itemPedido.getQuantidade(), itemPedido.getPreco());
        ProdutoDTO produto = new ProdutoDTO(itemPedido.getProduto());
        ItemPedidoPKDTO id = new ItemPedidoPKDTO(null, produto);
        setId(id);
    }

    @JsonIgnore
    public ProdutoDTO getProduto() {
        return getId().getProduto();
    }

    @JsonIgnore
    public PedidoDTO getPedido() {
        return getId().getPedido();
    }

    public ItemPedidoPKDTO getId() {
        return id;
    }

    public void setId(ItemPedidoPKDTO id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getSubTotal() {
        return (getPreco() - getDesconto()) * getQuantidade();
    }
}
