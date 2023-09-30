package com.lucassilvs.estudospring31java21.domain.ports.repositories.password;

public interface PasswordKeyboardComponent {

    public boolean validarSenha(char[][] teclado, int[] teclas, String hash);

}
