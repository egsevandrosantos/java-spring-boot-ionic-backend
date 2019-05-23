package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.*;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoComBoletoDTO;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoComCartaoDTO;
import com.evandrosantos.cursomc.dto.pedidos.PedidoDTO;
import com.evandrosantos.cursomc.repositories.PedidoRepository;
import com.evandrosantos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private ItemPedidoService itemPedidoService;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(() -> {
            throw new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %d, Tipo: Pedido", id));
        });
    }

    public Pedido insertOrUpdate(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getId() != null)
            find(pedidoDTO.getId());
        Pedido pedido = new Pedido(pedidoDTO);
        if (pedidoDTO.getPagamento() instanceof PagamentoComCartaoDTO) {
            PagamentoComCartaoDTO pagamentoComCartaoDTO = (PagamentoComCartaoDTO) pedidoDTO.getPagamento();
            pedido.setPagamento(new PagamentoComCartao(pagamentoComCartaoDTO, pedido));
        } else if (pedidoDTO.getPagamento() instanceof PagamentoComBoletoDTO) {
            PagamentoComBoletoDTO pagamentoComBoletoDTO = (PagamentoComBoletoDTO) pedidoDTO.getPagamento();
            pedido.setPagamento(new PagamentoComBoleto(pagamentoComBoletoDTO, pedido));
        }
        pedido.setCliente(clienteService.find(pedidoDTO.getCliente().getId()));
        pedido.setEnderecoDeEntrega(enderecoService.find(pedidoDTO.getEnderecoDeEntrega().getId()));
        pedido = repository.save(pedido);
        Set<ItemPedido> itens = itemPedidoService.insert(pedidoDTO.getItensPedido(), pedido);
        pedido.getItens().addAll(itens);
        return pedido;
    }
}
