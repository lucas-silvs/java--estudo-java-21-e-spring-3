package com.lucassilvs.estudospring31java21.domain.ports.interfaces;

import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.AtualizarDadosUsuarioModel;

public interface UsuarioPort {

    void cadastrarUsuario(UsuarioModel usuarioModel);

    UsuarioModel buscarUsuario(String identificador);

    void atualizarDadosUsuario (AtualizarDadosUsuarioModel atualizarDadosUsuarioModel);

    void excluirUsuario (String identificador);
}
