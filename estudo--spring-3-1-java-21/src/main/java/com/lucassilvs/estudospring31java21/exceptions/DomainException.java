package com.lucassilvs.estudospring31java21.exceptions;

public class DomainException extends UsuarioException{


    public DomainException(String mensagem, String mensagemInterna, String metodo, int status) {
        super(mensagem, mensagemInterna, metodo, LayerExceptionEnum.DOMAIN, status);
    }
}
