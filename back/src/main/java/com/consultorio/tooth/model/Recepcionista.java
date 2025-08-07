package com.consultorio.tooth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recepcionistas")
@PrimaryKeyJoinColumn(name = "idRecepcionista")
public class Recepcionista extends Usuario {

    // CONSTRUCTOR
    public Recepcionista() {
    }

    public Recepcionista(Long dni, String username, String contrasenia, String nombre, String apellido, LocalDate fechaNacimiento, String telefono, String correoElectronico, boolean enabled, boolean acountNotExpired, boolean acountNotLocked, boolean credentialNotExpired, Rol rol) {
        super(dni, username, contrasenia, nombre, apellido, fechaNacimiento, telefono, correoElectronico, enabled, acountNotExpired, acountNotLocked, credentialNotExpired, rol);
    }

}
