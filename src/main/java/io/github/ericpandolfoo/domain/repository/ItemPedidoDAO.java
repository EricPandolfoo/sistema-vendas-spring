package io.github.ericpandolfoo.domain.repository;

import io.github.ericpandolfoo.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoDAO extends JpaRepository<ItemPedido, Integer> {
}
