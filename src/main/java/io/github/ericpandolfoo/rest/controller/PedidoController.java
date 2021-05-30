package io.github.ericpandolfoo.rest.controller;

import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.repository.PedidosRepository;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;
import io.github.ericpandolfoo.service.PedidoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/order")
public class PedidoController {


    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(CREATED)
    public Integer createOrder(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }




}
