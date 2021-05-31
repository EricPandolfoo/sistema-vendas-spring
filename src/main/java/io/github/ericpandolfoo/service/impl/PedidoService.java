package io.github.ericpandolfoo.service.impl;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.enums.StatusPedido;
import io.github.ericpandolfoo.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;

import java.util.List;
import java.util.Optional;


public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> getPedidoById(Integer idPedido);
    void atualizarStatusPedido(Integer idPedido, StatusPedido status);
}
