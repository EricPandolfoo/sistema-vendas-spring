package io.github.ericpandolfoo.domain.repository;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PedidosDAO extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);
    }

