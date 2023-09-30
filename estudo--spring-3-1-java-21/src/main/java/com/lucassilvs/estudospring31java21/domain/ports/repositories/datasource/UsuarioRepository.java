package com.lucassilvs.estudospring31java21.domain.ports.repositories.datasource;

import com.lucassilvs.estudospring31java21.domain.models.usuario.AtualizarDadosUsuarioModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;

public interface UsuarioRepository {

    void  cadastrarUsuario(UsuarioModel usuarioModel);

    UsuarioModel buscarUsuario(String email);

    void atualizarDadosUsuario(AtualizarDadosUsuarioModel atualizarDadosUsuarioModel);

    void alterarCredencialUsuario(String email, String hashSenha);

    boolean emailUsuariocadastrado(String email);

    void excluirUsuario(String email);
}
