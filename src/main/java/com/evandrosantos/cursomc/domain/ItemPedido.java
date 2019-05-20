package com.evandrosantos.cursomc.domain;

import com.evandrosantos.cursomc.domain.pk.ItemPedidoPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() { }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        setId(pedido, produto);
        setDesconto(desconto);
        setQuantidade(quantidade);
        setPreco(preco);
    }

    public ItemPedido(Double desconto, Integer quantidade, Double preco) {
        this(null, null, desconto, quantidade, preco);
    }

    public Pedido getPedido() {
        return this.id.getPedido();
    }

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setId(Pedido pedido, Produto produto) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
