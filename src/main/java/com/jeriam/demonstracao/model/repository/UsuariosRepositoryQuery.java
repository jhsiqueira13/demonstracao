package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Usuarios;
import com.jeriam.demonstracao.model.filter.UsuarioFiltro;
import java.util.List;

/**
 *
 * @author jeriam
 */
public interface UsuariosRepositoryQuery {

    List<Usuarios> filtro(UsuarioFiltro filtro);
}
