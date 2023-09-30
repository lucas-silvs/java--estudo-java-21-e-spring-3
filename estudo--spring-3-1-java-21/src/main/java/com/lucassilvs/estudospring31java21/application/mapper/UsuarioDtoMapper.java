package com.lucassilvs.estudospring31java21.application.mapper;

import com.lucassilvs.estudospring31java21.application.models.AlterarSenhaDto;
import com.lucassilvs.estudospring31java21.application.models.AtualizarUsuarioDto;
import com.lucassilvs.estudospring31java21.application.models.ResetarSenhaDto;
import com.lucassilvs.estudospring31java21.application.models.UsuarioDto;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.AlterarCredencialModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.ResetarCredenciaisModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.AtualizarDadosUsuarioModel;
import com.lucassilvs.estudospring31java21.domain.models.usuario.UsuarioModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDtoMapper {


    UsuarioModel map(UsuarioDto usuarioDto);

    @InheritInverseConfiguration
    UsuarioDto map(UsuarioModel usuarioModel);

    AtualizarDadosUsuarioModel map(AtualizarUsuarioDto atualizarUsuarioDto);

    ResetarCredenciaisModel map(ResetarSenhaDto resetarSenhaDto);

    AlterarCredencialModel map(AlterarSenhaDto alterarSenhaDto);
}
