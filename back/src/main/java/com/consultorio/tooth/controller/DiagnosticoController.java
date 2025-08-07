package com.consultorio.tooth.controller;

import com.consultorio.tooth.model.Diagnostico;
import com.consultorio.tooth.service.interfaces.IDiagnosticoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnostico")
public class DiagnosticoController {

    @Autowired
    private IDiagnosticoService diagnosticoServ;

    @GetMapping("/get")
    public List<Diagnostico> getDiagnosticos() {
        return diagnosticoServ.getDiagnosticos();
    }

    @PostMapping("/save")
    public ResponseEntity<Diagnostico> saveDiagnostico(@RequestBody Diagnostico diagnostico) {

        return ResponseEntity.ok(diagnosticoServ.saveDiagnostico(diagnostico));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDiagnostico(@PathVariable Long id) {
        diagnosticoServ.deleteDiagnostico(id);

        return "El Diagnostico fue eliminado correctamente";
    }

    @GetMapping("/get/{id}")
    public Diagnostico getDiagnostico(@PathVariable Long id) {
        return diagnosticoServ.findDiagnostico(id);

    }

}
