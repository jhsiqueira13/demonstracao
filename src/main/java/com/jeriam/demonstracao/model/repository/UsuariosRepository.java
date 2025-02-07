package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Usuarios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jeriam
 */
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>, UsuariosRepositoryQuery {

    Optional<Usuarios> findByEmail(String email);
}
