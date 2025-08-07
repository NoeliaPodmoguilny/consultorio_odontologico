package com.consultorio.tooth.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "odontologos")
@PrimaryKeyJoinColumn(name = "idOdontologo")
public class Odontologo extends Usuario {

    // RELACION CON LA TABLA TRATAMIENTO
    @ManyToMany
    @JoinTable(
            name = "odontologo_tratamiento",
            joinColumns = @JoinColumn(name = "idOdontologo"),
            inverseJoinColumns = @JoinColumn(name = "idTratamiento"))
    private List<Tratamiento> tratamiento;

    public Odontologo() {
    }

    public Odontologo(List<Tratamiento> tratamiento) {
        this.tratamiento = tratamiento;
    }

    public List<Tratamiento> getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(List<Tratamiento> tratamiento) {
        this.tratamiento = tratamiento;
    }

    
}
