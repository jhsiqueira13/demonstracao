package com.jeriam.demonstracao.controller;

import com.jeriam.demonstracao.model.entity.Usuarios;
import com.jeriam.demonstracao.model.filter.UsuarioFiltro;
import com.jeriam.demonstracao.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jeriam
 */
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(path = "/eu", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Usuarios> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuarios currentUser = (Usuarios) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Usuarios>> buscarTodos() {
        List<Usuarios> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @RequestMapping(path = "/{usuarioId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Usuarios>> buscar(@PathVariable Long usuarioId) {
        List<Usuarios> usuarios = usuarioService.buscar(usuarioId);
        return ResponseEntity.ok(usuarios);
    }

    @RequestMapping(path = "/filtrar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<Usuarios>> filtrar(@RequestBody UsuarioFiltro filtro) {
        List<Usuarios> usuarios = usuarioService.filtrar(filtro);
        return ResponseEntity.ok(usuarios);
    }

}
