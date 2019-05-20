package com.evandrosantos.cursomc.repositories;

import com.evandrosantos.cursomc.domain.ItemPedido;
import com.evandrosantos.cursomc.domain.pk.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
}
