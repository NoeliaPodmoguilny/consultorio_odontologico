
package com.consultorio.tooth.controller;

import com.consultorio.tooth.model.Recepcionista;
import com.consultorio.tooth.service.interfaces.IRecepcionistaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recepcionista")
public class RecepcionistaController {

    @Autowired
    private IRecepcionistaService recepServ;


//    traer lista de recepcionstas
    @GetMapping("/get")
    public List<Recepcionista> getRecepcionistas() {
        return recepServ.getRecepcionistas();
    }

    //    traer un recepcionista por id
    @GetMapping("/get/{id}")
    public Recepcionista getRecepcionista(@PathVariable Long id) {
        return recepServ.findRecepcionista(id);
    }

    // Editar recepcionista
    @PutMapping("/update/{id}")
    public void updateRecepcionista(@PathVariable Long id, @RequestBody Recepcionista recep) {
        recepServ.updateRecepcionista(id, recep);
    }

    //    eliminar recepcionista
    @DeleteMapping("/delete/{id}")
    public String deleteRecepcionista(@PathVariable Long id) {
        recepServ.deleteRecepcionista(id);
        return "La recepcionista fue eliminada correctamente";
    }

    // Crear recepcionista
    @PostMapping("/save")
    public ResponseEntity<Recepcionista> createRecep(@RequestBody Recepcionista recepcionista) {
        recepServ.saveRecepcionista(recepcionista);
        return ResponseEntity.ok(recepcionista);
    }

}

