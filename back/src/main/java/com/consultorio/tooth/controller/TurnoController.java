package com.consultorio.tooth.controller;

import com.consultorio.tooth.dto.turno.TurnoDTO;
import com.consultorio.tooth.model.Turno;
import com.consultorio.tooth.service.interfaces.ITurnoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/turno")
public class TurnoController {

    @Autowired
    private ITurnoService turnoServ;

//    traer lista de turnos
    @GetMapping("/getTurnos")
    public List<Turno> getTurnos() {
        return turnoServ.getTurnos();
    }

    @PostMapping("/save")
    public ResponseEntity<Turno> createTurno(@RequestBody TurnoDTO turno) {

        Turno newTurno = turnoServ.saveTurno(turno);
        return ResponseEntity.ok(newTurno);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTurno(@PathVariable Long id) {
        turnoServ.deleteTurno(id);
        return ResponseEntity.ok("El Turno fue cancelado  correctamente");
    }

    @GetMapping("/get/{id}")
    public Turno getTurno(@PathVariable Long id) {
        return turnoServ.findTurno(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id, @RequestBody Turno turno) {
        turnoServ.updateTurno(id, turno);
        return ResponseEntity.ok(turno);
    }

    @PutMapping("/updateEstado/{id}")
    public ResponseEntity<String> updateEstadoTurno(@PathVariable Long id) {
        turnoServ.updateEstadoTurno(id);
        return ResponseEntity.ok("Turno actualizado");
    }

    @GetMapping("/getTurnosPorOdontologo/{username}")
    public List<Turno> getTurnosPorOdontologo(@PathVariable String username) {
        return turnoServ.getTurnosPorOdontologo(username);
    }

    @GetMapping("/getTurnosPorPaciente/{idPaciente}")
    public List<Turno> getTurnosPorPaciente(@PathVariable Long idPaciente) {
        return turnoServ.getTurnosPorPaciente(idPaciente);
    }

}
