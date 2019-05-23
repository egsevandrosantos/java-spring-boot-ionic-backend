package com.evandrosantos.cursomc.dto.itensPedidos;

import com.evandrosantos.cursomc.dto.pedidos.PedidoDTO;
import com.evandrosantos.cursomc.dto.produtos.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ItemPedidoPKDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private PedidoDTO pedido;
    private ProdutoDTO produto;

    public ItemPedidoPKDTO() { }

    public ItemPedidoPKDTO(PedidoDTO pedidoDTO, ProdutoDTO produtoDTO) {
        setPedido(pedidoDTO);
        setProduto(produtoDTO);
    }

    @JsonIgnore
    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedidoDTO) {
        this.pedido = pedidoDTO;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produtoDTO) {
        this.produto = produtoDTO;
    }
}
