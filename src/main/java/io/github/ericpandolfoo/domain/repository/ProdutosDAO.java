package io.github.ericpandolfoo.domain.repository;

import io.github.ericpandolfoo.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosDAO extends JpaRepository<Produto, Integer> {
}
