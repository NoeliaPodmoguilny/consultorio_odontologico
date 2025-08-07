package com.consultorio.tooth.controller;

import com.consultorio.tooth.model.Tratamiento;
import com.consultorio.tooth.service.interfaces.ITratamientoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tratamiento")
public class TratamientoController {

    @Autowired
    private ITratamientoService tratamientoServ;

//    traer lista de tratamientos
    @GetMapping("/get")
    public List<Tratamiento> getTratamientos() {
        return tratamientoServ.getTratamientos();
    }

// crear tratamiento
    @PostMapping("/save")
    public ResponseEntity<Tratamiento> saveTratamiento(@RequestBody Tratamiento tratam) {
        return ResponseEntity.ok(tratamientoServ.saveTratamiento(tratam)); 
    }

//    eliminar tratamiento
    @DeleteMapping("/delete/{id}")
    public String deleteTratamiento(@PathVariable Long id) {
        tratamientoServ.deleteTratamiento(id);

        return "El tratamiento fue eliminado correctamente";
    }

//    traer un tratamiento por id
    @GetMapping("/get/{id}")
    public Tratamiento getTratamiento(@PathVariable Long id) {
        return tratamientoServ.findTratamiento(id);

    }
}
