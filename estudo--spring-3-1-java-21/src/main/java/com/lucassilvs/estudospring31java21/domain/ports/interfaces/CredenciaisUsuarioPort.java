package com.lucassilvs.estudospring31java21.domain.ports.interfaces;

import com.lucassilvs.estudospring31java21.domain.models.credenciais.AlterarCredencialModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.CredencialsModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.CredencialsTecladoVirtualModel;
import com.lucassilvs.estudospring31java21.domain.models.credenciais.ResetarCredenciaisModel;

public interface CredenciaisUsuarioPort {


    void validarSenha (CredencialsModel credencialsModel);


    void validarSenhaTecladoVirtual (CredencialsTecladoVirtualModel credencialsModel);


    void alterarCredenciais (AlterarCredencialModel alterarCredencialModel);

    void resetarCredenciais (ResetarCredenciaisModel resetarCredenciaisModel);
}
