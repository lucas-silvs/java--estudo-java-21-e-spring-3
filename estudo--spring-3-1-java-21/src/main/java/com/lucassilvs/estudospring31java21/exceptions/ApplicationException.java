package com.lucassilvs.estudospring31java21.exceptions;

public class ApplicationException extends UsuarioException{

    public ApplicationException(String mensagem, String mensagemInterna, String metodo, int status) {
        super(mensagem, mensagemInterna, metodo, LayerExceptionEnum.APPLICATION, status);
    }
}
