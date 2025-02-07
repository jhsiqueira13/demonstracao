package com.jeriam.demonstracao.service;

import com.jeriam.demonstracao.exceptions.UsuariosNaoEncontradosException;
import com.jeriam.demonstracao.exceptions.UsuariosValidationException;
import com.jeriam.demonstracao.model.entity.Usuarios;
import com.jeriam.demonstracao.model.filter.UsuarioFiltro;
import com.jeriam.demonstracao.validations.ValidacaoUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeriam.demonstracao.model.repository.UsuariosRepository;

/**
 *
 * @author jeriam
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository repository;

    public List<Usuarios> buscarTodos() {
        return repository.findAll();
    }

    public List<Usuarios> buscar(Long usuarioId) {
        if (usuarioId == null) {
            throw new UsuariosValidationException("Id do Usuário é obrigatório");
        }

        return repository.findById(usuarioId)
                .map(List::of)
                .orElseThrow(() -> new UsuariosNaoEncontradosException("Usuário não encontrado"));
    }

    public List<Usuarios> filtrar(UsuarioFiltro usuarioFiltro) {
        return repository.filtro(usuarioFiltro);
    }

    public Usuarios salvar(Usuarios usuario) throws Exception {
        ValidacaoUsuario.validar(usuario);
        return repository.save(usuario);
    }

    public void deletarPeloId(Long usuarioId) {
        Usuarios usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new UsuariosNaoEncontradosException("Usuário não encontrado com ID: " + usuarioId));
        repository.delete(usuario);
    }

}
