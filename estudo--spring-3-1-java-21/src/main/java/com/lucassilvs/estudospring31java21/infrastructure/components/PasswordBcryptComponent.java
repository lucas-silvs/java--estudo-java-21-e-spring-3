package com.lucassilvs.estudospring31java21.infrastructure.components;


import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordComponent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordBcryptComponent implements PasswordComponent {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String gerarHash(String senha) {
        return encoder.encode(senha);
    }

    @Override
    public boolean validarSenha(String senha, String hash) {
        return encoder.matches(senha,hash);
    }
}
