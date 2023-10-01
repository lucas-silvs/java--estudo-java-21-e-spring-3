package com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.mapper;

import com.lucassilvs.estudospring31java21.domain.models.usuario.AtualizarDadosUsuarioModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;
import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.entity.UsuarioJpaEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    UsuarioJpaEntity map(UsuarioModel usuarioModel);

    @InheritInverseConfiguration
    UsuarioModel map(UsuarioJpaEntity usuarioJpaEntity);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "senha", ignore = true)
    void atualizarUsuarioJpaEntity(AtualizarDadosUsuarioModel usuarioModel, @MappingTarget UsuarioJpaEntity usuarioJpaEntity);
}
