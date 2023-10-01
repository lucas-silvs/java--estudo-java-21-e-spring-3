package com.lucassilvs.estudospring31java21.infrastructure.datasource.relational;

import com.lucassilvs.estudospring31java21.domain.models.usuario.AtualizarDadosUsuarioModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.datasource.UsuarioRepository;
import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.entity.UsuarioJpaEntity;
import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.mapper.UsuarioEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {


    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository, UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    @Transactional
    public void cadastrarUsuario(UsuarioModel usuarioModel) {
        UsuarioJpaEntity usuarioJpaEntity = usuarioEntityMapper.map(usuarioModel);
        usuarioJpaRepository.save(usuarioJpaEntity);
    }

    @Override
    public UsuarioModel buscarUsuario(String email) {
        UsuarioJpaEntity usuarioJpaEntity = usuarioJpaRepository.findByEmail(email);
        return usuarioEntityMapper.map(usuarioJpaEntity);
    }

    @Override
    @Transactional
    public void atualizarDadosUsuario(AtualizarDadosUsuarioModel atualizarDadosUsuarioModel) {
        UsuarioJpaEntity usuarioJpaEntity = usuarioJpaRepository.findByEmail(atualizarDadosUsuarioModel.email());
        usuarioEntityMapper.atualizarUsuarioJpaEntity(atualizarDadosUsuarioModel, usuarioJpaEntity);
        usuarioJpaRepository.save(usuarioJpaEntity);
    }

    @Override
    @Transactional
    public void alterarCredencialUsuario(String email, String hashSenha) {
        UsuarioJpaEntity usuarioJpaEntity = usuarioJpaRepository.findByEmail(email);
        usuarioJpaEntity.setSenha(hashSenha);
        usuarioJpaRepository.save(usuarioJpaEntity);
    }

    @Override
    public boolean emailUsuariocadastrado(String email) {
        return usuarioJpaRepository.existsByEmail(email);
    }

    @Transactional
    public void excluirUsuario(String email) {
        usuarioJpaRepository.deleteByEmail(email);
    }
}
