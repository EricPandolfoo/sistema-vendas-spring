package io.github.ericpandolfoo.domain.repository;

import io.github.ericpandolfoo.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

    @Query(value = "select * from usuario where login =:login ", nativeQuery = true)
    Optional<Usuario> confirmUserExist(@Param("login") String login);
}


  //  @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
  //  List<Cliente> encontrarPorNome(@Param("nome") String nome);