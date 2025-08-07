package com.consultorio.tooth.dto.turno;

import java.time.LocalDate;
import java.time.LocalTime;


public class TurnoDTO {

    private Long tratamiento;
    private Long odontologo;
    private LocalDate fecha;
    private LocalTime hora;
    private String username;

    public TurnoDTO() {
    }

    public TurnoDTO(Long tratamiento, Long odontologo, LocalDate fecha, LocalTime hora, String username) {
        this.tratamiento = tratamiento;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
        this.username = username;
    }

    public Long getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Long tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Long getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Long odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
 

}
