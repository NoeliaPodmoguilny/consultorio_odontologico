package com.consultorio.tooth.controller;

import com.consultorio.tooth.model.Usuario;
import com.consultorio.tooth.service.interfaces.IUsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")

public class UsuarioController {

    @Autowired
    private IUsuarioService usuServ;

    @GetMapping("/getUsuarios")
    public ResponseEntity<List> getUsuarios() {
        List usuarios = usuServ.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // OBTENER UN USUARIO POR DNI (que es el ID)
    @GetMapping("/getUsuario")
    public ResponseEntity getUsuarioByDni(@RequestBody Long dni) {
        Optional usuario = usuServ.getUsuarioByDni(dni);
        return (ResponseEntity) usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // OBTENER USUARIO POR USERNAME
    @GetMapping("/{username}")
    public Optional<Usuario> getUserByUsername(@PathVariable String username) {
        return usuServ.findUsuByUsername(username);
    }

}
