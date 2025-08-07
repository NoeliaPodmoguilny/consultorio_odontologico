package com.consultorio.tooth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "idPaciente")
public class Paciente extends Usuario {

    public Paciente() {
    }

    public Paciente(Long dni, String username, String contrasenia, String nombre, String apellido, LocalDate fechaNacimiento, String telefono, String correoElectronico, boolean enabled, boolean acountNotExpired, boolean acountNotLocked, boolean credentialNotExpired, Rol rol) {
        super(dni, username, contrasenia, nombre, apellido, fechaNacimiento, telefono, correoElectronico, enabled, acountNotExpired, acountNotLocked, credentialNotExpired, rol);
    }

}
