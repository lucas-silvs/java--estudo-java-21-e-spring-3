package com.lucassilvs.estudospring31java21.exceptions;

public abstract class UsuarioException extends RuntimeException{

    private String mensagem;

    private String mensagemInterna;

    private String metodo;

    private final LayerExceptionEnum camada;
    private int status;

    public UsuarioException(String mensagem, String mensagemInterna, String metodo, LayerExceptionEnum camada, int status) {
        this.mensagem = mensagem;
        this.mensagemInterna = mensagemInterna;
        this.metodo = metodo;
        this.camada = camada;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getMensagemInterna() {
        return mensagemInterna;
    }

    public String getMetodo() {
        return metodo;
    }

    public LayerExceptionEnum getCamada() {
        return camada;
    }

    public int getStatus() {
        return status;
    }
}
