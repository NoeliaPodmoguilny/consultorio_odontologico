package com.consultorio.tooth.dto.historialMedico;

import com.consultorio.tooth.model.Tratamiento;
import java.time.LocalDate;
import java.util.List;

public class HistorialMedicoDTO {

    private Long idHistorialMedico;
    private LocalDate fechaIngreso;

    private Long pacienteDNI;

    // Datos personales
    private String genero;
    private int edad;

    // Dirección
    private String calle;
    private String altura;
    private String ciudad;
    private String provincia;

    // Salud general
    private String estadoSalud;
    private int peso;
    private int alturaCm;

    // Antecedentes médicos
//    @ElementCollection
    private List<String> condiciones;
    private String medicamentos;

    // Hábitos
//    @ElementCollection
    private List<String> habitos;
    private String otrosHabitos;

    // Historial familiar
//    @ElementCollection
    private List<String> historialFamiliar;
    private String otrosHistorialFamiliar;

    // Embarazo (solo para pacientes femeninas)
    private String embarazo;

    // Seguro médico
    private String seguroMedico;
    private String seguroMedicoDescripcion;

    // Información adicional
    private String comentarios;

    // Contacto de emergencia
    private String contactoNombre;
    private String contactoTelefono;
    private String contactoRelacionConPaciente;

    private String tipoDiagnostico;
    private LocalDate fechaDiagnostico;
    private List<Tratamiento> tratamientos;

    public HistorialMedicoDTO() {
    }

    public HistorialMedicoDTO(Long idHistorialMedico, LocalDate fechaIngreso, Long pacienteDNI, String genero, int edad, String calle, String altura, String ciudad, String provincia, String estadoSalud, int peso, int alturaCm, List<String> condiciones, String medicamentos, List<String> habitos, String otrosHabitos, List<String> historialFamiliar, String otrosHistorialFamiliar, String embarazo, String seguroMedico, String seguroMedicoDescripcion, String comentarios, String contactoNombre, String contactoTelefono, String contactoRelacionConPaciente, String tipoDiagnostico, LocalDate fechaDiagnostico, List<Tratamiento> tratamientos) {
        this.idHistorialMedico = idHistorialMedico;
        this.fechaIngreso = fechaIngreso;
        this.pacienteDNI = pacienteDNI;
        this.genero = genero;
        this.edad = edad;
        this.calle = calle;
        this.altura = altura;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.estadoSalud = estadoSalud;
        this.peso = peso;
        this.alturaCm = alturaCm;
        this.condiciones = condiciones;
        this.medicamentos = medicamentos;
        this.habitos = habitos;
        this.otrosHabitos = otrosHabitos;
        this.historialFamiliar = historialFamiliar;
        this.otrosHistorialFamiliar = otrosHistorialFamiliar;
        this.embarazo = embarazo;
        this.seguroMedico = seguroMedico;
        this.seguroMedicoDescripcion = seguroMedicoDescripcion;
        this.comentarios = comentarios;
        this.contactoNombre = contactoNombre;
        this.contactoTelefono = contactoTelefono;
        this.contactoRelacionConPaciente = contactoRelacionConPaciente;
        this.tipoDiagnostico = tipoDiagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.tratamientos = tratamientos;
    }

    public Long getIdHistorialMedico() {
        return idHistorialMedico;
    }

    public void setIdHistorialMedico(Long idHistorialMedico) {
        this.idHistorialMedico = idHistorialMedico;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getPacienteDNI() {
        return pacienteDNI;
    }

    public void setPacienteDNI(Long pacienteDNI) {
        this.pacienteDNI = pacienteDNI;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(int alturaCm) {
        this.alturaCm = alturaCm;
    }

    public List<String> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<String> condiciones) {
        this.condiciones = condiciones;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<String> getHabitos() {
        return habitos;
    }

    public void setHabitos(List<String> habitos) {
        this.habitos = habitos;
    }

    public String getOtrosHabitos() {
        return otrosHabitos;
    }

    public void setOtrosHabitos(String otrosHabitos) {
        this.otrosHabitos = otrosHabitos;
    }

    public List<String> getHistorialFamiliar() {
        return historialFamiliar;
    }

    public void setHistorialFamiliar(List<String> historialFamiliar) {
        this.historialFamiliar = historialFamiliar;
    }

    public String getOtrosHistorialFamiliar() {
        return otrosHistorialFamiliar;
    }

    public void setOtrosHistorialFamiliar(String otrosHistorialFamiliar) {
        this.otrosHistorialFamiliar = otrosHistorialFamiliar;
    }

    public String getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(String embarazo) {
        this.embarazo = embarazo;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public String getSeguroMedicoDescripcion() {
        return seguroMedicoDescripcion;
    }

    public void setSeguroMedicoDescripcion(String seguroMedicoDescripcion) {
        this.seguroMedicoDescripcion = seguroMedicoDescripcion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public String getContactoRelacionConPaciente() {
        return contactoRelacionConPaciente;
    }

    public void setContactoRelacionConPaciente(String contactoRelacionConPaciente) {
        this.contactoRelacionConPaciente = contactoRelacionConPaciente;
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
