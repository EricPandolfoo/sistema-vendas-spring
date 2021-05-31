package io.github.ericpandolfoo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesPedidoDTO {
    private Integer codigo;
    private String nomeCliente;
    private String cpf;
    private String dataPedido;
    private BigDecimal totalPedido;
    private String status;
    private List<InformacaoItemPedidoDTO> items;
}
