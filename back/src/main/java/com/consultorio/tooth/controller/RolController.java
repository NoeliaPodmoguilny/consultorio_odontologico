package com.consultorio.tooth.controller;

import com.consultorio.tooth.model.Rol;
import com.consultorio.tooth.service.interfaces.IRolService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private IRolService rolService;


    // DEVUELVE UNA LISTA CON LOS REGISTROS
    @GetMapping("/get")
    public ResponseEntity<List> getAllRoles() {
        List roles = rolService.findAllRoles();
        return ResponseEntity.ok(roles);
    }

    // OBTIENE UN ROL POR ID
    @GetMapping("/get/{id}")
    public ResponseEntity getRolById(@PathVariable Long id) {
        Optional rol = rolService.findRolById(id);
        return (ResponseEntity) rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREA UN NUEVO ROL
    @PostMapping("/save")
    public ResponseEntity createRol(@RequestBody Rol rol) {
        Rol newRol = rolService.saveRol(rol);
        return ResponseEntity.ok(newRol);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRolById(@PathVariable Long id) {
        rolService.deleteRolById(id);
        return ResponseEntity.ok("Rol eliminado correctamente");
    }


}
