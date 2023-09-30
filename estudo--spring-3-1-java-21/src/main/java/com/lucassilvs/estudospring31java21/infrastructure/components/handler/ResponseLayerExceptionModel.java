package com.lucassilvs.estudospring31java21.infrastructure.components.handler;

public class ResponseLayerExceptionModel {

    private String layer;
    private String message;

    public ResponseLayerExceptionModel(String layer, String message) {
        this.layer = layer;
        this.message = message;
    }

    public String getLayer() {
        return layer;
    }

    public String getMessage() {
        return message;
    }

}
