package com.lucassilvs.estudospring31java21.domain.models.credenciais;

public record AlterarCredencialModel(

        String email,
        String senhaAtual,
        String novaSenha

) {
}
