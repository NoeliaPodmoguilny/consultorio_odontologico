package com.consultorio.tooth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
//  CLASE MADRE (CLASE ABSTRACTA)
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    //ATRIBUTOS
    @Id
    @Column(nullable = false, unique = true) // NO PERMITE VALORES NULOS
    private Long dni;
    @Column(unique = true) // NO PERMITE REPETIDOS
    private String username;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correoElectronico;

//   CORRESPODE A LOS PERMISOS 
    private boolean enabled;
    private boolean acountNotExpired;
    private boolean acountNotLocked;
    private boolean credentialNotExpired;

// RELACION CON LA TABLA ROL
    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Long dni, String username, String contrasenia, String nombre, String apellido, LocalDate fechaNacimiento, String telefono, String correoElectronico, boolean enabled, boolean acountNotExpired, boolean acountNotLocked, boolean credentialNotExpired, Rol rol) {
        this.dni = dni;
        this.username = username;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.enabled = enabled;
        this.acountNotExpired = acountNotExpired;
        this.acountNotLocked = acountNotLocked;
        this.credentialNotExpired = credentialNotExpired;
        this.rol = rol;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAcountNotExpired() {
        return acountNotExpired;
    }

    public void setAcountNotExpired(boolean acountNotExpired) {
        this.acountNotExpired = acountNotExpired;
    }

    public boolean isAcountNotLocked() {
        return acountNotLocked;
    }

    public void setAcountNotLocked(boolean acountNotLocked) {
        this.acountNotLocked = acountNotLocked;
    }

    public boolean isCredentialNotExpired() {
        return credentialNotExpired;
    }

    public void setCredentialNotExpired(boolean credentialNotExpired) {
        this.credentialNotExpired = credentialNotExpired;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
