package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Emprestimos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jeriam
 */
public interface EmprestimosRepository extends JpaRepository<Emprestimos, Long> {

}
