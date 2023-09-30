package com.lucassilvs.estudospring31java21.application.models;

import jakarta.validation.constraints.NotEmpty;

public record AtualizarUsuarioDto(

        @NotEmpty(message = "Email não pode ser vazia")
        String email,
        String nome,
        String telefone
) {
}
