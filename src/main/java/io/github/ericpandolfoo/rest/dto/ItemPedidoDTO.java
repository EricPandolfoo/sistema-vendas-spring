package io.github.ericpandolfoo.rest.dto;


import io.github.ericpandolfoo.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {
 *  "cliente": 1,
 *  "total": 100,
 *  "items": [
 *              {
 *                  "produto": 1,
 *                  "quantidade": 10
 *              }
 *          ]
 * }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private Integer produto;
    private Integer quantidade;
}
