package com.jeriam.demonstracao.model.repository;

import com.jeriam.demonstracao.model.entity.Clientes;
import com.jeriam.demonstracao.model.filter.ClienteFiltro;
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
public class ClientesRepositoryImpl implements ClientesRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Clientes> filtro(ClienteFiltro filtro) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Clientes> criteria = cb.createQuery(Clientes.class);

        Root<Clientes> root = criteria.from(Clientes.class);
        criteria.select(root).distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
            predicates.add(cb.like(root.get("nome"), filtro.getNome() + "%"));
        }
        if (filtro.getCidade() != null && !filtro.getCidade().isBlank()) {
            predicates.add(cb.equal(root.get("cidade"), filtro.getCidade()));
        }
        if (filtro.getEstado() != null && !filtro.getEstado().isBlank()) {
            predicates.add(cb.equal(root.get("estado"), filtro.getEstado()));
        }
        if (!predicates.isEmpty()) {
            criteria.where(predicates.stream().toArray(Predicate[]::new));
        }
        TypedQuery<Clientes> query = manager.createQuery(criteria);
        return query
                .getResultList();
    }

}
