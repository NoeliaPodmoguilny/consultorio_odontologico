package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Paciente;
import java.util.List;


public interface IPacienteService {

    public String encriptPassword(String contrasenia);
    
    public List<Paciente> getPacientes();
    public Paciente findPaciente(Long id);
    public void deletePaciente(Long id);
    public Paciente updatePaciente(Long id, Paciente paciente);
    public Paciente savePaciente(Paciente paciente);

    public Paciente getPacienteByUsername(String usernamePaciente);

    public List<Paciente> getPacientesPorOdontologo(Long idOdontologo);

    

}
