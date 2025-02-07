package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.entity.MovimentacoesBancarias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jeriam
 */
public interface MovimentacoesBancariasRepository extends JpaRepository<MovimentacoesBancarias, Long> {

    List<MovimentacoesBancarias> findByClientesOrderByDataHoraDesc(Clientes cliente);
    
}
