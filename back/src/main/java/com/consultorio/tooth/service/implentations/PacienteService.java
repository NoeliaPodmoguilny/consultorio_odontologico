package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.service.interfaces.IPacienteService;
import com.consultorio.tooth.model.Paciente;
import com.consultorio.tooth.model.Rol;
import com.consultorio.tooth.repository.interfaces.IDiagnosticoRepository;
import com.consultorio.tooth.repository.interfaces.IHisorialMedicoRepository;
import com.consultorio.tooth.repository.interfaces.IPacienteRepository;
import com.consultorio.tooth.service.interfaces.IRolService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepo;

    @Autowired
    private IRolService rolServ;

    @Autowired
    private IDiagnosticoRepository diagnosticoRepo;

    @Autowired
    private IHisorialMedicoRepository historialMedicoRepo;

    @Override
    public String encriptPassword(String contrasenia) {
        return new BCryptPasswordEncoder().encode(contrasenia);
    }

    @Override
    public List<Paciente> getPacientes() {
        return pacienteRepo.findAll();
    }

    @Override
    public Paciente findPaciente(Long id) {
        Paciente pac = pacienteRepo.findById(id).orElse(null);
        return pac;
    }

    @Override
    public Paciente savePaciente(Paciente paciente) {

        // Instancia de nuevo paciente
        Paciente newPaciente = new Paciente();

        // ENCRIPTAR CONTRASEÑA
        newPaciente.setContrasenia(this.encriptPassword(paciente.getContrasenia()));

        // Recuperar el Rol por su ID 
        Optional<Rol> optionalRol = rolServ.findRolById(paciente.getRol().getIdRol());
        optionalRol.ifPresent(newPaciente::setRol);  // Solo asignamos el rol si existe

        // Seteamos el resto de los valores al nuevo obj paciente
        newPaciente.setNombre(paciente.getNombre());
        newPaciente.setApellido(paciente.getApellido());
        newPaciente.setUsername(paciente.getUsername());
        newPaciente.setDni(paciente.getDni());
        newPaciente.setTelefono(paciente.getTelefono());
        newPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        newPaciente.setCorreoElectronico(paciente.getCorreoElectronico());

        Paciente pac = pacienteRepo.save(newPaciente);

        return pac;

    }

    @Override
    public void deletePaciente(Long id) {
        pacienteRepo.deleteById(id);
    }

    @Override
    public Paciente updatePaciente(Long id, Paciente paciente) {

        Paciente pac = this.findPaciente(id);

        pac.setNombre(paciente.getNombre());
        pac.setApellido(paciente.getApellido());
        pac.setFechaNacimiento(paciente.getFechaNacimiento());
        pac.setCorreoElectronico(paciente.getCorreoElectronico());

        return pacienteRepo.save(pac);

    }

    @Override
    public Paciente getPacienteByUsername(String usernamePaciente) {
        return pacienteRepo.findPacienteByUsername(usernamePaciente);
    }

    @Override
    public List<Paciente> getPacientesPorOdontologo(Long idOdontologo) {
        List<Paciente> listaPaci = pacienteRepo.getPacientesPorOdontologo(idOdontologo);
        System.out.println("pacientes " + listaPaci);
        return listaPaci;
    }


}
