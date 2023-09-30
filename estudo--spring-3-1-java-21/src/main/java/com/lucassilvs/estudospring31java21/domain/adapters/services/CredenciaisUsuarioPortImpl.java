package com.lucassilvs.estudospring31java21.domain.adapters.services;

import com.lucassilvs.estudospring31java21.domain.models.credenciais.AlterarCredencialModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.CredencialsModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.CredencialsTecladoVirtualModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.ResetarCredenciaisModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.CredenciaisUsuarioPort;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.datasource.UsuarioRepository;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordComponent;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordKeyboardComponent;
import com.lucassilvs.estudospring31java21.exceptions.DomainException;

public class CredenciaisUsuarioPortImpl implements CredenciaisUsuarioPort {


    public static final String SENHA_INVALIDA = "Senha inválida";
    private final UsuarioRepository usuarioRepository;

    private final PasswordComponent passwordComponent;

    private final PasswordKeyboardComponent passwordKeyboardComponent;

    public CredenciaisUsuarioPortImpl(UsuarioRepository usuarioRepository, PasswordComponent passwordComponent, PasswordKeyboardComponent passwordKeyboardComponent) {
        this.usuarioRepository = usuarioRepository;
        this.passwordComponent = passwordComponent;
        this.passwordKeyboardComponent = passwordKeyboardComponent;
    }
    @Override
    public void validarSenha(CredencialsModel credencialsModel) {

        validarCadastroEmail(credencialsModel.email());

        UsuarioModel usuarioModel = usuarioRepository.buscarUsuario(credencialsModel.email());

        if (!passwordComponent.validarSenha(credencialsModel.senha(), usuarioModel.senha())) {
            throw new DomainException(SENHA_INVALIDA, SENHA_INVALIDA, this.getClass().getEnclosingMethod().getName(), 401);
        }

    }

    @Override
    public void validarSenhaTecladoVirtual(CredencialsTecladoVirtualModel credencialsModel) {

        validarCadastroEmail(credencialsModel.email());

        UsuarioModel usuarioModel = usuarioRepository.buscarUsuario(credencialsModel.email());

        if (!passwordKeyboardComponent.validarSenha(credencialsModel.teclado(), credencialsModel.teclas(), usuarioModel.senha())) {
            throw new DomainException(SENHA_INVALIDA, SENHA_INVALIDA, this.getClass().getEnclosingMethod().getName(), 401);
        }
    }

    @Override
    public void alterarCredenciais(AlterarCredencialModel alterarCredencialModel) {

        validarCadastroEmail(alterarCredencialModel.email());

        UsuarioModel usuarioModel = usuarioRepository.buscarUsuario(alterarCredencialModel.email());

        if (!passwordComponent.validarSenha(alterarCredencialModel.senhaAtual(), usuarioModel.senha())) {
            throw new DomainException(SENHA_INVALIDA, SENHA_INVALIDA, this.getClass().getEnclosingMethod().getName(), 401);
        }

        usuarioRepository.alterarCredencialUsuario(alterarCredencialModel.email(), passwordComponent.gerarHash(alterarCredencialModel.novaSenha()));

    }

    @Override
    public void resetarCredenciais(ResetarCredenciaisModel resetarCredenciaisModel) {

        validarCadastroEmail(resetarCredenciaisModel.email());

        usuarioRepository.alterarCredencialUsuario(resetarCredenciaisModel.email(), passwordComponent.gerarHash(resetarCredenciaisModel.novaSenha()));

    }

    private void validarCadastroEmail(String email) {
        if (!usuarioRepository.emailUsuariocadastrado(email)) {
            throw new DomainException(String.format("Email %s do usuario não está cadastrado", email), "Usuario Não está cadastrado", this.getClass().getEnclosingMethod().getName(), 409);
        }
    }
}
