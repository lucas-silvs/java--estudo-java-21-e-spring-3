package com.lucassilvs.estudospring31java21.infrastructure.datasource.relational;

import com.lucassilvs.estudospring31java21.infrastructure.datasource.relational.entity.UsuarioJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends CrudRepository<UsuarioJpaEntity, String> {

    boolean existsByEmail(String email);

    UsuarioJpaEntity findByEmail(String email);

    void deleteByEmail(String email);

}
