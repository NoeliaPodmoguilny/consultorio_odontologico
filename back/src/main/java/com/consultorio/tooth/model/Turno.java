package com.consultorio.tooth.model;

import com.consultorio.tooth.enums.EstadoTurno;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "turnos")
public class Turno {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurno;
    @Enumerated(EnumType.STRING)
    private EstadoTurno estado;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;

//    RELACIÓN CON LA TABLA USUARIO
    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    //    RELACIÓN CON LA TABLA ODONTOLOGO
    @ManyToOne
    @JoinColumn(name = "idOdontologo")
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "idTratamiento")
    private Tratamiento tratamiento;

    public Turno() {
    }

    public Turno(Long idTurno, EstadoTurno estado, LocalDate fechaTurno, LocalTime horaTurno, Paciente paciente, Odontologo odontologo, Tratamiento tratamiento) {
        this.idTurno = idTurno;
        this.estado = estado;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.tratamiento = tratamiento;
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    
}
