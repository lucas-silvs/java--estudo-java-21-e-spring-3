package com.lucassilvs.estudospring31java21.application.mapper;


import com.lucassilvs.estudospring31java21.application.models.AlterarSenhaDto;
import com.lucassilvs.estudospring31java21.application.models.CredencialUsuarioDto;
import com.lucassilvs.estudospring31java21.application.models.CredencialUsuarioTecladoDto;
import com.lucassilvs.estudospring31java21.application.models.ResetarSenhaDto;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.AlterarCredencialModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.CredencialsModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.CredencialsTecladoVirtualModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.ResetarCredenciaisModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialDtoMapper {

    CredencialsTecladoVirtualModel map(CredencialUsuarioTecladoDto credencialUsuarioTecladoDto );

    CredencialsModel map(CredencialUsuarioDto credencialUsuarioDto);

    ResetarCredenciaisModel map(ResetarSenhaDto resetarSenhaDto);

    AlterarCredencialModel map(AlterarSenhaDto alterarSenhaDto);
}
