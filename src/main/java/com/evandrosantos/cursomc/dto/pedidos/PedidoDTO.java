package com.evandrosantos.cursomc.dto.pedidos;

import com.evandrosantos.cursomc.domain.PagamentoComBoleto;
import com.evandrosantos.cursomc.domain.PagamentoComCartao;
import com.evandrosantos.cursomc.domain.Pedido;
import com.evandrosantos.cursomc.dto.clientes.ClienteDTO;
import com.evandrosantos.cursomc.dto.enderecos.EnderecoDTO;
import com.evandrosantos.cursomc.dto.itensPedidos.ItemPedidoDTO;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoComBoletoDTO;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoComCartaoDTO;
import com.evandrosantos.cursomc.dto.pagamentos.PagamentoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PedidoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;
    private PagamentoDTO pagamento;
    private ClienteDTO cliente;
    private EnderecoDTO enderecoDeEntrega;
    private Set<ItemPedidoDTO> itensPedido = new HashSet<>();

    public PedidoDTO() { }

    public PedidoDTO(Integer id, Date instante, PagamentoDTO pagamento, ClienteDTO cliente, EnderecoDTO enderecoDeEntrega) {
        setId(id);
        setInstante(instante);
        setPagamento(pagamento);
        setCliente(cliente);
        setEnderecoDeEntrega(enderecoDeEntrega);
    }

    public PedidoDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getInstante(), null, null, null);
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
            setPagamento(new PagamentoComBoletoDTO(pagamento));
        } else if (pedido.getPagamento() instanceof PagamentoComCartao) {
            PagamentoComCartao pagamento = (PagamentoComCartao) pedido.getPagamento();
            setPagamento(new PagamentoComCartaoDTO(pagamento));
        }
        ClienteDTO cliente = new ClienteDTO(pedido.getCliente());
        setCliente(cliente);
        EnderecoDTO enderecoDeEntrega = new EnderecoDTO(pedido.getEnderecoDeEntrega());
        setEnderecoDeEntrega(enderecoDeEntrega);
        Set<ItemPedidoDTO> itensPedidos = pedido.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toSet());
        setItensPedido(itensPedidos);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public EnderecoDTO getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(EnderecoDTO enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(Set<ItemPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Double getValorTotal() {
        double valor = 0.0;
        for (ItemPedidoDTO item : getItensPedido()) {
            valor += item.getSubTotal();
        }
        return valor;
    }
}
