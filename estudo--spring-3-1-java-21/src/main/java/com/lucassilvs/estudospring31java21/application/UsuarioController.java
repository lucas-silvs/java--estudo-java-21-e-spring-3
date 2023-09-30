package com.lucassilvs.estudospring31java21.application;

import com.lucassilvs.estudospring31java21.application.models.*;

public interface UsuarioController {

    void CadastrarUsuario(UsuarioDto userDto);

    UsuarioDto buscarUsuarioPorEmail(String email);

    void atualizarUsuario(AtualizarUsuarioDto atualizarUsuarioDto);

    void deletarUsuario(String email);

    void autenticarUsuarioTeclado(CredencialUsuarioTecladoDto credencialUsuarioTecladoDTO);

    void autenticarUsuarioSenha(CredencialUsuarioDto credencialUsuarioDTO);

    void alterarSenha(AlterarSenhaDto alterarSenhaDto);

    void resetarSenha(ResetarSenhaDto resetarSenhaDto);

}
