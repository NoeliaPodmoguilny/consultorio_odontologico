package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.dto.turno.TurnoDTO;
import com.consultorio.tooth.model.Odontologo;
import com.consultorio.tooth.model.Turno;
import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {

    public List<Turno> getTurnos();

    public Turno saveTurno(TurnoDTO turno);

    public void deleteTurno(Long id);

    public Turno findTurno(Long id);

    public Turno updateTurno(Long id, Turno turno);

    public void updateEstadoTurno(Long id);

    public List<Turno> getTurnosPorOdontologo(String username);

    public List<LocalDate> getFechasDisponibles(Odontologo odontologo, LocalDate fechaInicio, LocalDate fechaFin);

    public List<Turno> getTurnosPorPaciente(Long idPaciente);

}
