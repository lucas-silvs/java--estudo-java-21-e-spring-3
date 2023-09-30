package com.lucassilvs.estudospring31java21.infrastructure.configuration;


import com.lucassilvs.estudospring31java21.domain.adapters.services.CredenciaisUsuarioPortImpl;
import com.lucassilvs.estudospring31java21.domain.adapters.services.UsuarioPortImpl;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.CredenciaisUsuarioPort;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.UsuarioPort;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.datasource.UsuarioRepository;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordComponent;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordKeyboardComponent;
import com.lucassilvs.estudospring31java21.infrastructure.components.PasswordBcryptComponent;
import com.lucassilvs.estudospring31java21.infrastructure.components.PasswordTecladoVirtualComponent;
import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.UsuarioJpaRepository;
import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.UsuarioRepositoryImpl;
import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.mapper.UsuarioEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UsuarioRepository usuarioRepository(UsuarioJpaRepository usuarioJpaRepository) {
        return new UsuarioRepositoryImpl(usuarioJpaRepository, UsuarioEntityMapper.INSTANCE);
    }

    @Bean
    public UsuarioPort usuarioPort(UsuarioRepository usuarioRepository, PasswordComponent passwordComponent) {
        return new UsuarioPortImpl(usuarioRepository, passwordComponent);
    }

    @Bean
    public CredenciaisUsuarioPort credenciaisUsuarioPort(UsuarioRepository usuarioRepository, PasswordComponent passwordComponent, PasswordKeyboardComponent passwordKeyboardComponent) {
        return new CredenciaisUsuarioPortImpl(usuarioRepository, passwordComponent, passwordKeyboardComponent);
    }
    @Bean
    public PasswordComponent passwordComponent() {
        return new PasswordBcryptComponent();
    }

    @Bean
    public PasswordKeyboardComponent passwordKeyboardComponent(PasswordComponent passwordComponent) {
        return new PasswordTecladoVirtualComponent(passwordComponent);
    }
}
