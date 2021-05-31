package io.github.ericpandolfoo.service.impl.impl;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.ItemPedido;
import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.entity.Produto;
import io.github.ericpandolfoo.domain.enums.StatusPedido;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import io.github.ericpandolfoo.domain.repository.ItemPedidoRepository;
import io.github.ericpandolfoo.domain.repository.PedidosRepository;
import io.github.ericpandolfoo.domain.repository.ProdutosRepository;
import io.github.ericpandolfoo.exception.PedidoNaoEncontradoException;
import io.github.ericpandolfoo.exception.RegraNegocioException;
import io.github.ericpandolfoo.rest.dto.ItemPedidoDTO;
import io.github.ericpandolfoo.rest.dto.PedidoDTO;
import io.github.ericpandolfoo.service.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    ClientesRepository clientesRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    @Autowired
    PedidosRepository pedidosRepository;
    @Autowired
    ProdutosRepository produtosRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() ->
                        new RegraNegocioException("Cliente não encontrado, id: " + idCliente));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedidos = convertItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> getPedidoById(Integer idPedido) {
        return pedidosRepository.findByIdFetchItens(idPedido);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido status) {
        pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatusPedido(status);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(
                () -> new PedidoNaoEncontradoException());
    }


    private List<ItemPedido> convertItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return items.stream()
                .map(itemPedidoDTO -> {
                    Integer idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException("Produto não encontrado, id: " + idProduto));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
