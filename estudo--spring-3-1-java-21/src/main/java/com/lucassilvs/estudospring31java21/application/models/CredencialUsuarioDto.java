package com.lucassilvs.estudospring31java21.application.models;

import jakarta.validation.constraints.NotEmpty;

public record CredencialUsuarioDto(

        @NotEmpty
        String email,

        @NotEmpty
        String senha
) {
}
