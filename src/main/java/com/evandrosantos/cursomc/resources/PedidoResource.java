package com.evandrosantos.cursomc.resources;

import com.evandrosantos.cursomc.domain.Pedido;
import com.evandrosantos.cursomc.dto.pedidos.PedidoDTO;
import com.evandrosantos.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PedidoDTO> find(@PathVariable Integer id) {
        Pedido pedido = service.find(id);
        PedidoDTO pedidoDTO = new PedidoDTO(pedido);
        return ResponseEntity.ok().body(pedidoDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody PedidoDTO pedidoDTO) {
        pedidoDTO.setId(null);
        Pedido pedido = service.insertOrUpdate(pedidoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
