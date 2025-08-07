package com.consultorio.tooth.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiagostico;
    private String tipoDiagnostico; //ODONTOGRAMA??
    private LocalDate fechaDiagnostico;
    // RELACION CON LA TABLA TRATAMIENTO
    @ManyToMany
    @JoinTable(
            name = "diagnostico_tratamiento",
            joinColumns = @JoinColumn(name = "idDiagostico"),
            inverseJoinColumns = @JoinColumn(name = "idTratamiento"))
    private List<Tratamiento> tratamientos;

    public Diagnostico() {
    }

    public Diagnostico(Long idDiagostico, String tipoDiagnostico, LocalDate fechaDiagnostico, List<Tratamiento> tratamientos) {
        this.idDiagostico = idDiagostico;
        this.tipoDiagnostico = tipoDiagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.tratamientos = tratamientos;
    }

    public Long getIdDiagostico() {
        return idDiagostico;
    }

    public void setIdDiagostico(Long idDiagostico) {
        this.idDiagostico = idDiagostico;
    }

    public String getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(String tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public LocalDate getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(LocalDate fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

}
