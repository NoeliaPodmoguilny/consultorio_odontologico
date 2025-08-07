package com.consultorio.tooth.controller;


import com.consultorio.tooth.model.Paciente;
import com.consultorio.tooth.service.interfaces.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteServ;

//    traer lista de pacientes
    @GetMapping("/get")
    public List<Paciente> getPacientes() {
        return pacienteServ.getPacientes();
    }

//    traer un Paciente por id
    @GetMapping("/get/{id}")
    public ResponseEntity<Paciente> getPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteServ.findPaciente(id));
    }

// crear Paciente
    @PostMapping("/save")
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente newrecep = pacienteServ.savePaciente(paciente);
        return ResponseEntity.ok(newrecep);

    }

//    eliminar Paciente
    @DeleteMapping("/delete/{id}")
    public String deletePaciente(@PathVariable Long id) {
        pacienteServ.deletePaciente(id);

        return "El Paciente fue eliminado correctamente";
    }

    // Editar 
    @PutMapping("/update/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteServ.updatePaciente(id, paciente));
    }

    @GetMapping("/getPacientesPorOdontologo/{idOdontologo}")
    public ResponseEntity< List<Paciente>> getPacientesPorOdontologo(@PathVariable Long idOdontologo) {
        return ResponseEntity.ok(pacienteServ.getPacientesPorOdontologo(idOdontologo));
    }

    @GetMapping("/getPacienteByUsername/{usernamePaciente}")
    public ResponseEntity<Paciente> getPacienteByUsername(@PathVariable String usernamePaciente){
        return ResponseEntity.ok(pacienteServ.getPacienteByUsername(usernamePaciente));
    }
    



}
