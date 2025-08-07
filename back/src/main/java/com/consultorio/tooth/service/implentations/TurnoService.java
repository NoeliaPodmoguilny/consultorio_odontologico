package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.dto.turno.TurnoDTO;
import com.consultorio.tooth.enums.EstadoTurno;
import com.consultorio.tooth.model.Odontologo;
import com.consultorio.tooth.model.Paciente;
import com.consultorio.tooth.model.Tratamiento;
import com.consultorio.tooth.service.interfaces.ITurnoService;
import com.consultorio.tooth.model.Turno;
import com.consultorio.tooth.repository.interfaces.IOdontologoRepository;
import com.consultorio.tooth.repository.interfaces.ITurnoRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService {

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private OdontologoService odontologoServ;

    @Autowired
    private PacienteService pacienteServ;

    @Autowired
    private TratamientoService tratamientoServ;

    // traer lista de turnos
    @Override
    public List<Turno> getTurnos() {
        return turnoRepository.findAll();
    }

    // eliminar turno por id
    @Override
    public void deleteTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    // traer un turno por su id
    @Override
    public Turno findTurno(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    // Actualizar turno
    @Override
    public Turno updateTurno(Long id, Turno turno) {

        Turno tur = this.findTurno(id);

        tur.setEstado(turno.getEstado());
        tur.setFechaTurno(turno.getFechaTurno());
        tur.setHoraTurno(turno.getHoraTurno());

        Turno newTurno = turnoRepository.save(tur);
        return newTurno;
    }

    // Crear turno
    @Override
    public Turno saveTurno(TurnoDTO turno) {
        //creo nuevo turno
        Turno newTurno = new Turno();

        // Seteo fecha y hora
        newTurno.setFechaTurno(turno.getFecha());
        newTurno.setHoraTurno(turno.getHora());

        // Obtener el usuario logueado desde el SecurityContextHolder
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernamePaciente = turno.getUsername();  // El nombre de usuario extraído del token JWT

        // Asociar el paciente logueado al turno
        Paciente paciente = pacienteServ.getPacienteByUsername(usernamePaciente);  // busca el paciente usando el username del token
        newTurno.setPaciente(paciente);
        System.out.println("Paciente : " + paciente);

        // seteo el estado del turno
        newTurno.setEstado(EstadoTurno.PROGRAMADO);

        // Recupero el tratamiento por su ID 
        Tratamiento tratamiento = (Tratamiento) tratamientoServ.findTratamiento(turno.getTratamiento());
        newTurno.setTratamiento(tratamiento);

        // recupero odontologo por id
        Odontologo readOdontologo = (Odontologo) odontologoServ.findOdontologo(turno.getOdontologo());
        newTurno.setOdontologo(readOdontologo);
        System.out.println("Turno guardado: " + newTurno);

        turnoRepository.save(newTurno);

        return newTurno;
    }

    // Obtener la lista de turnos de un odontologo
    @Override
    public List<Turno> getTurnosPorOdontologo(String username) {
        return turnoRepository.getTurnosPorOdontologo(username);
    }

    // Obtener fechas disponibles
    @Override
    public List<LocalDate> getFechasDisponibles(Odontologo odontologo, LocalDate fechaInicio, LocalDate fechaFin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Turno> getTurnosPorPaciente(Long idPaciente) {
        return turnoRepository.getTurnosPorPaciente(idPaciente);
    }

    @Override
    public void updateEstadoTurno(Long id) {
        Turno turnoUpdate = this.findTurno(id);

        turnoUpdate.setEstado(EstadoTurno.CONFIRMADO);
        turnoRepository.save(turnoUpdate);
    }

}
