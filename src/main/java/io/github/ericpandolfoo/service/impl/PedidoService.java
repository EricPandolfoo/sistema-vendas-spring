package io.github.ericpandolfoo.service.impl;

import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;

import java.util.Optional;


public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> getPedidoById(Integer idPedido);
}
