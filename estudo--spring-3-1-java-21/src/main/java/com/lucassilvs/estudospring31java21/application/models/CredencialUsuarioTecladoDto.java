package com.lucassilvs.estudospring31java21.application.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CredencialUsuarioTecladoDto(

        @NotEmpty(message = "Email não pode ser vazia")
        String email,

        @NotNull(message = "Teclado não pode ser vazio")
        char[][] teclado,

        @NotNull(message = "Teclas não pode ser vazio")
        int[] teclas
) {
}
