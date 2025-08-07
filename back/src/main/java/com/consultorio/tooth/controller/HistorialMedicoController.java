package com.consultorio.tooth.controller;

import com.consultorio.tooth.dto.historialMedico.HistorialMedicoDTO;
import com.consultorio.tooth.model.HistorialMedico;
import com.consultorio.tooth.service.interfaces.IHistorialMedicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/historialMedico")
public class HistorialMedicoController {

    @Autowired
    private IHistorialMedicoService historialMedServ;

//    traer lista de lista_historial_medico
    @GetMapping("/get")
    public List<HistorialMedico> getHistorialMed() {
        return historialMedServ.getHistorialMed();
    }
// crear HistorialMedico

    @PostMapping("/save")
    public ResponseEntity<HistorialMedico> saveHisotrialMed(@RequestBody HistorialMedicoDTO historialmedicoDTO) {
        return ResponseEntity.ok(historialMedServ.save(historialmedicoDTO));
    }

//    eliminar HistorialMedico
    @DeleteMapping("/delete/{id}")
    public String deleteHistorialMed(@PathVariable Long id) {
        historialMedServ.deleteHistorialMed(id);

        return "El HistorialMedico fue eliminado correctamente";
    }

//    traer un HistorialMedico por id
    @GetMapping("/get/{id}")
    public ResponseEntity<HistorialMedico> getHistorialMed(@PathVariable Long id) {
        return ResponseEntity.ok(historialMedServ.findHistorialMed(id));

    }

}
