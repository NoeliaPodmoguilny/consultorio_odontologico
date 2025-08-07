package com.consultorio.tooth.dto.inicio;

public class UsuarioIngresoDTO {

    private String username;
    private String contrasenia;

    public UsuarioIngresoDTO() {
    }

    public UsuarioIngresoDTO(String username, String contrasenia) {
        this.username = username;
        this.contrasenia = contrasenia;
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

}
