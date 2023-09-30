package com.lucassilvs.estudospring31java21.domain.adapters.services;

import com.lucassilvs.estudospring31java21.domain.models.usuario.AtualizarDadosUsuarioModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.UsuarioPort;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.datasource.UsuarioRepository;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.exceptions.UsuarioNaoEncontradoException;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordComponent;
import com.lucassilvs.estudospring31java21.exceptions.DomainException;

public class UsuarioPortImpl implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;

    private final PasswordComponent passwordComponent;

    public UsuarioPortImpl(UsuarioRepository usuarioRepository, PasswordComponent passwordComponent) {
        this.usuarioRepository = usuarioRepository;
        this.passwordComponent = passwordComponent;
    }

    @Override
    public void cadastrarUsuario(UsuarioModel usuarioModel) {

        if (usuarioRepository.emailUsuariocadastrado(usuarioModel.email())) {
            throw new DomainException(String.format("Email já %s cadastrado", usuarioModel.email()), "email já cadastrado", this.getClass().getEnclosingMethod().getName(), 409);
        }

        UsuarioModel usuarioSenhaCriptografada = new UsuarioModel(usuarioModel.nome(),
                passwordComponent.gerarHash(usuarioModel.senha()),
                usuarioModel.email(),
                usuarioModel.telefone());

        usuarioRepository.cadastrarUsuario(usuarioSenhaCriptografada);

    }

    @Override
    public UsuarioModel buscarUsuario(String identificador) {

        try {
             return usuarioRepository.buscarUsuario(identificador);

        }catch (UsuarioNaoEncontradoException e){
            throw new DomainException("Usuário não encontrado", "Usuário não encontrado", this.getClass().getEnclosingMethod().getName(), 404);
        }
    }

    @Override
    public void atualizarDadosUsuario(AtualizarDadosUsuarioModel atualizarDadosUsuarioModel) {

        if (!usuarioRepository.emailUsuariocadastrado(atualizarDadosUsuarioModel.email())) {
            throw new DomainException(String.format("Email %s do usuario não está cadastrado", atualizarDadosUsuarioModel.email()), "Usuario Não está cadastrado", this.getClass().getEnclosingMethod().getName(), 409);
        }

        usuarioRepository.atualizarDadosUsuario(atualizarDadosUsuarioModel);

    }
    @Override
    public void excluirUsuario(String identificador) {

        if (!usuarioRepository.emailUsuariocadastrado(identificador)) {
            throw new DomainException(String.format("Email %s do usuario não está cadastrado", identificador), "Usuario Não está cadastrado", this.getClass().getEnclosingMethod().getName(), 409);
        }

        usuarioRepository.excluirUsuario(identificador);
    }
}
