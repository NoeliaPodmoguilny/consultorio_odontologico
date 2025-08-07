package com.consultorio.tooth.controller;

import com.consultorio.tooth.model.Odontologo;
import com.consultorio.tooth.service.interfaces.IOdontologoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/odontologo")
public class OdontologoController {

    @Autowired
    private IOdontologoService odontologoServ;

//    traer lista de odontologos
    @GetMapping("/get")
    public List<Odontologo> getOdontologos() {
        return odontologoServ.getOdontologos();
    }

    //    traer un Odontologo por dni (id)
    @GetMapping("/get/{id}")
    public Odontologo getOdontologo(@PathVariable Long id) {
        return odontologoServ.findOdontologo(id);
    }

    @GetMapping("/getByUsername/{username}")
    public Odontologo findOdontologoByUsername(@PathVariable String username) {
        return odontologoServ.findOdontologoByUsername(username);
    }

    // crear Odontologo
    @PostMapping("/save")
    public ResponseEntity<Odontologo> createOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo odonto = odontologoServ.saveOdontologo(odontologo);
        return ResponseEntity.ok(odonto);
    }

    //  eliminar Odontologo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOdontologo(@PathVariable Long id) {
        odontologoServ.deleteOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

    // editar odontologo
    @PutMapping("/update/{id}")
    public ResponseEntity<Odontologo> updateOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        odontologoServ.updateOdontologo(id, odontologo);
        return ResponseEntity.ok(odontologo);
    }

    // TRAER LISTA DE ODONTOLOGOS QUE TENGAN UN TRATAMIENTO ESPECÍFICO
    @GetMapping("/listaOdontologoPorTratamiento/{idTratamiento}")
    private List<Odontologo> getOdontologosPorTratamiento(@PathVariable Long idTratamiento) {
        return odontologoServ.getOdontologosPorTratamiento(idTratamiento);
    }


}
