package com.lucassilvs.estudospring31java21.exceptions;

public class InfrastructureException extends UsuarioException{


    public InfrastructureException(String mensagem, String mensagemInterna, String metodo, int status) {
        super(mensagem, mensagemInterna, metodo, LayerExceptionEnum.INFRASTRUCTURE, status);
    }
}
