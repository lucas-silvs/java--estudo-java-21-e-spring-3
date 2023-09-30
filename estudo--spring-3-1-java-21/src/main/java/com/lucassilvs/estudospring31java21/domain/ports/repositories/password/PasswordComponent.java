package com.lucassilvs.estudospring31java21.domain.ports.repositories.password;

public interface PasswordComponent {

    public String gerarHash(String senha);

    public boolean validarSenha(String senha, String hash);

}
