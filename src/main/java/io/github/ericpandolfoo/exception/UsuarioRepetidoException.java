package io.github.ericpandolfoo.exception;

public class UsuarioRepetidoException extends RuntimeException {

    public UsuarioRepetidoException(String usuario) {
        super("Usuário: " + usuario + ", já cadastrado no sistema.");
    }

}
