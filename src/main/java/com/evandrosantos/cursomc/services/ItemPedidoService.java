package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.ItemPedido;
import com.evandrosantos.cursomc.domain.Pedido;
import com.evandrosantos.cursomc.domain.Produto;
import com.evandrosantos.cursomc.dto.itensPedidos.ItemPedidoDTO;
import com.evandrosantos.cursomc.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository repository;
    @Autowired
    private ProdutoService produtoService;

    public Set<ItemPedido> insert(Set<ItemPedidoDTO> dtos, Pedido pedido) {
        Set<ItemPedido> list = new HashSet<>();
        for (ItemPedidoDTO dto : dtos) {
            ItemPedido itemPedido = new ItemPedido(dto);
            Produto produto = produtoService.find(dto.getProduto().getId());
            itemPedido.setProduto(produto);
            itemPedido.setPreco(produto.getPreco());
            itemPedido.setPedido(pedido);
            itemPedido = repository.save(itemPedido);
            list.add(itemPedido);
        }
        return list;
    }
}
