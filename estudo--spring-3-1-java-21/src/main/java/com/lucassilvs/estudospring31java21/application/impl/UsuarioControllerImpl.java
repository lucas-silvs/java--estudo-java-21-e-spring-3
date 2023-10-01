package com.lucassilvs.estudospring31java21.application.impl;


import com.lucassilvs.estudospring31java21.application.UsuarioController;
import com.lucassilvs.estudospring31java21.application.mapper.CredentialDtoMapper;
import com.lucassilvs.estudospring31java21.application.mapper.UsuarioDtoMapper;
import com.lucassilvs.estudospring31java21.application.models.*;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.CredenciaisUsuarioPort;
import com.lucassilvs.estudospring31java21.domain.ports.interfaces.UsuarioPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void CadastrarUsuario(@Valid @RequestBody UsuarioDto userDto) {
        usuarioPort.cadastrarUsuario(usuarioDtoMapper.map(userDto));
    }

    @GetMapping
    public UsuarioDto buscarUsuarioPorEmail(@RequestParam String email) {
        return usuarioDtoMapper.map(usuarioPort.buscarUsuario(email));
    }

    @PutMapping
    public void atualizarUsuario(@Valid @RequestBody AtualizarUsuarioDto atualizarUsuarioDto) {
        usuarioPort.atualizarDadosUsuario(usuarioDtoMapper.map(atualizarUsuarioDto));
    }

    @DeleteMapping
    public void deletarUsuario(@RequestParam String email) {
        usuarioPort.excluirUsuario(email);
    }

    @PostMapping("/autenticar/teclado")
    public void autenticarUsuarioTeclado(@Valid @RequestBody CredencialUsuarioTecladoDto credencialUsuarioTecladoDTO) {
        credenciaisUsuarioPort.validarSenhaTecladoVirtual(credentialDtoMapper.map(credencialUsuarioTecladoDTO));
    }

    @PostMapping("/autenticar/senha")
    public void autenticarUsuarioSenha(@Valid @RequestBody CredencialUsuarioDto credencialUsuarioDTO) {
        credenciaisUsuarioPort.validarSenha(credentialDtoMapper.map(credencialUsuarioDTO));

    }

    @PutMapping("alterar/senha")
    public void alterarSenha(@Valid @RequestBody AlterarSenhaDto alterarSenhaDto) {
        credenciaisUsuarioPort.alterarCredenciais(credentialDtoMapper.map(alterarSenhaDto));
    }

    @PutMapping("resetar/senha")
    public void resetarSenha(@Valid @RequestBody ResetarSenhaDto resetarSenhaDto) {
        credenciaisUsuarioPort.resetarCredenciais(credentialDtoMapper.map(resetarSenhaDto));
    }

}
