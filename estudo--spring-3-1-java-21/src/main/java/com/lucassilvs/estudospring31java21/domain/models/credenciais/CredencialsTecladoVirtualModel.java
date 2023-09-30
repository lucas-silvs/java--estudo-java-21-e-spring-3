package com.lucassilvs.estudospring31java21.domain.models.credenciais;

public record CredencialsTecladoVirtualModel(

        String email,
         char[][] teclado,
         int[] teclas
) {
}
