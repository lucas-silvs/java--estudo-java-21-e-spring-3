package com.lucassilvs.estudospring31java21.domain.ports.repositories.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{


    private String mensagem;

    public UsuarioNaoEncontradoException(String mensagem) {
        this.mensagem = mensagem;
    }
}
