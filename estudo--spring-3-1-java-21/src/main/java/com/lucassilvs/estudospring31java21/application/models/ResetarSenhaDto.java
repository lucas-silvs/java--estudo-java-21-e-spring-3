package com.lucassilvs.estudospring31java21.application.models;

import jakarta.validation.constraints.NotEmpty;

public record ResetarSenhaDto(

        @NotEmpty(message = "Email não pode ser vazia")
        String email,

        @NotEmpty(message = "Senha não pode ser vazia")
        String novaSenha
) {

}
