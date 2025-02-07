package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Usuarios;
import com.jeriam.demonstracao.model.filter.UsuarioFiltro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeriam
 */
public class UsuariosRepositoryImpl implements UsuariosRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Usuarios> filtro(UsuarioFiltro filtro) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Usuarios> criteria = cb.createQuery(Usuarios.class);

        Root<Usuarios> root = criteria.from(Usuarios.class);
        criteria.select(root).distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
            predicates.add(cb.like(root.get("nome"), filtro.getNome() + "%"));
        }
        if (filtro.getEmail() != null && !filtro.getEmail().isBlank()) {
            predicates.add(cb.equal(root.get("email"), filtro.getEmail()));
        }
        if (filtro.getDataCriacao() != null && !filtro.getDataCriacao().isBlank()) {
            predicates.add(cb.equal(root.get("dataCriacao"), filtro.getDataCriacao()));
        }
        if (!predicates.isEmpty()) {
            criteria.where(predicates.stream().toArray(Predicate[]::new));
        }
        TypedQuery<Usuarios> query = manager.createQuery(criteria);
        return query
                .getResultList();
    }

}
