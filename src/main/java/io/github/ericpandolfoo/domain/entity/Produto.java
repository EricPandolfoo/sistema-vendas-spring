package io.github.ericpandolfoo.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao")
    @NotEmpty(message = "O campo descrição é obrigatório.")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "O campo preço é obrigatório.")
    private BigDecimal preco;


}


