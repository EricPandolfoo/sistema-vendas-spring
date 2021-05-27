package io.github.ericpandolfoo.domain.repository;

import io.github.ericpandolfoo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);
}
