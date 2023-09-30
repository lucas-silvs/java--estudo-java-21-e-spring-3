package com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity(name = "usuario")
public class UsuarioJpaEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @UuidGenerator
    private String id;

    private String nome;
    private String senha;
    private String email;
    private String telefone;

    public UsuarioJpaEntity(String nome, String senha, String email, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }

    public UsuarioJpaEntity() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
