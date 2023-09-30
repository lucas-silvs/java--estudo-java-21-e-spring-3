package com.lucassilvs.estudospring31java21.application.impl;


import com.lucassilvs.estudospring31java21.application.UsuarioController;
import com.lucassilvs.estudospring31java21.application.mapper.CredentialDtoMapper;
import com.lucassilvs.estudospring31java21.application.mapper.UsuarioDtoMapper;
import com.lucassilvs.estudospring31java21.application.models.*;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.CredenciaisUsuarioPort;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.UsuarioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioPort usuarioPort;

    private final CredenciaisUsuarioPort credenciaisUsuarioPort;

    private final UsuarioDtoMapper usuarioDtoMapper;

    private final CredentialDtoMapper credentialDtoMapper;

    @Autowired
    public UsuarioControllerImpl(UsuarioPort usuarioPort, CredenciaisUsuarioPort credenciaisUsuarioPort, UsuarioDtoMapper usuarioDtoMapper, CredentialDtoMapper credentialDtoMapper) {
        this.usuarioPort = usuarioPort;
        this.credenciaisUsuarioPort = credenciaisUsuarioPort;
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.credentialDtoMapper = credentialDtoMapper;
    }

    @PostMapping
    public void CadastrarUsuario(UsuarioDto userDto) {
        usuarioPort.cadastrarUsuario(usuarioDtoMapper.map(userDto));
    }

    @GetMapping
    public UsuarioDto buscarUsuarioPorEmail(String email) {
        return usuarioDtoMapper.map(usuarioPort.buscarUsuario(email));
    }

    @Override
    public void atualizarUsuario(AtualizarUsuarioDto atualizarUsuarioDto) {
        usuarioPort.atualizarDadosUsuario(usuarioDtoMapper.map(atualizarUsuarioDto));
    }

    @Override
    public void deletarUsuario(String email) {
        usuarioPort.excluirUsuario(email);
    }

    @Override
    public void autenticarUsuarioTeclado(CredencialUsuarioTecladoDto credencialUsuarioTecladoDTO) {
        credenciaisUsuarioPort.validarSenhaTecladoVirtual(credentialDtoMapper.map(credencialUsuarioTecladoDTO));
    }

    @Override
    public void autenticarUsuarioSenha(CredencialUsuarioDto credencialUsuarioDTO) {
        credenciaisUsuarioPort.validarSenha(credentialDtoMapper.map(credencialUsuarioDTO));

    }

    @Override
    public void alterarSenha(AlterarSenhaDto alterarSenhaDto) {
        credenciaisUsuarioPort.alterarCredenciais(credentialDtoMapper.map(alterarSenhaDto));
    }

    @Override
    public void resetarSenha(ResetarSenhaDto resetarSenhaDto) {
        credenciaisUsuarioPort.resetarCredenciais(credentialDtoMapper.map(resetarSenhaDto));
    }

}
