package com.lucassilvs.estudospring31java21.application.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioDto(

        @NotEmpty(message = "Nome não pode ser vazio")
        String nome,

        @NotEmpty(message = "Senha não pode ser vazia")
        String senha,

        @NotEmpty(message = "Email não pode ser vazio")
        String email,

        @NotEmpty(message = "Telefone não pode ser vazio")
        String telefone

) {

}
