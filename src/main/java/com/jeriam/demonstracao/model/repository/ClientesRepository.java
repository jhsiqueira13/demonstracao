package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Clientes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jeriam
 */
public interface ClientesRepository extends JpaRepository<Clientes, Long>, ClientesRepositoryQuery {

    Optional<Clientes> findById(Long id);
    
}
