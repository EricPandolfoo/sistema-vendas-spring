package io.github.ericpandolfoo.service;

import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;
import org.springframework.stereotype.Service;

@Service
public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
