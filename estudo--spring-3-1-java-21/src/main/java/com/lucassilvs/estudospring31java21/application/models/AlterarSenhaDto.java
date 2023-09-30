package com.lucassilvs.estudospring31java21.application.models;

import jakarta.validation.constraints.NotEmpty;

public record AlterarSenhaDto(

        @NotEmpty(message = "Email não pode ser vazia")
        String email,

        @NotEmpty(message = "Senha atual não pode ser vazia")
        String senhaAtual,

        @NotEmpty(message = "Nova senha não pode ser vazia")
        String novaSenha

) {

}
