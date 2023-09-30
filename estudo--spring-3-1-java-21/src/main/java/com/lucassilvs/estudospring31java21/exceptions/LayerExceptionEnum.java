package com.lucassilvs.estudospring31java21.exceptions;

public enum LayerExceptionEnum {

    DOMAIN("Domain Layer"),
    APPLICATION("Application Layer"),
    ENTRYPOINT("Entrypoint Layer"),
    INFRASTRUCTURE("Infrastructure Layer");

    private final String displayName;

    LayerExceptionEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
