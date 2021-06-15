package io.github.ericpandolfoo.exception;

public class ClienteRepetidoException extends RuntimeException {

    public ClienteRepetidoException(String cpf) {
        super("Cliente com CPF: " + cpf + " já cadastrado no sistema");
    }
}
