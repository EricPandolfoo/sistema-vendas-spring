package io.github.ericpandolfoo.rest.controller;

import io.github.ericpandolfoo.domain.entity.ItemPedido;
import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.enums.StatusPedido;
import io.github.ericpandolfoo.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.ericpandolfoo.rest.dto.InformacaoItemPedidoDTO;
import io.github.ericpandolfoo.rest.dto.InformacoesPedidoDTO;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;
import io.github.ericpandolfoo.service.impl.PedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public InformacoesPedidoDTO getOrderById(@PathVariable("id") Integer id) {

        return service
                .getPedidoById(id)
                .map(p -> converterPedido(p))
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado, id: " + id));
    }


    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id,
                             @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converterPedido(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .cpf(pedido.getCliente().getCpf())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nomeCliente(pedido.getCliente().getNome())
                .totalPedido(pedido.getTotal())
                .status(pedido.getStatusPedido().name())
                .items(converterItemPedido(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converterItemPedido(List<ItemPedido> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        return items.stream().map(
                itemPedido -> InformacaoItemPedidoDTO
                        .builder()
                        .descricao(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
