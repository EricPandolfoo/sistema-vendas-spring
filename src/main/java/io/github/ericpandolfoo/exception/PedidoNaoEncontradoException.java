package io.github.ericpandolfoo.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado");
    }

    public PedidoNaoEncontradoException(Integer id) {
        super("Pedido de id: " + id + " não encontrado");
    }
}
