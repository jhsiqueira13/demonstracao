package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.filter.ClienteFiltro;
import java.util.List;

/**
 *
 * @author jeriam
 */
public interface ClientesRepositoryQuery {

    List<Clientes> filtro(ClienteFiltro filtro);
}
