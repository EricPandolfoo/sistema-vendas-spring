package io.github.ericpandolfoo.service.impl;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.ItemPedido;
import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.entity.Produto;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import io.github.ericpandolfoo.domain.repository.ItemPedidoRepository;
import io.github.ericpandolfoo.domain.repository.PedidosRepository;
import io.github.ericpandolfoo.domain.repository.ProdutosRepository;
import io.github.ericpandolfoo.exception.RegraNegocioException;
import io.github.ericpandolfoo.rest.dto.ItemPedidoDTO;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;
import io.github.ericpandolfoo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() ->
                        new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setId(pedido.getId());
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível criar um pedido sem itens");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto  = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() ->
                                    new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
