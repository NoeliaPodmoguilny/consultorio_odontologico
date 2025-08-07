package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public List getUsuarios();

    public Optional getUsuarioByDni(Long dni);

    public Optional<Usuario> findUsuByUsername(String username);

    public String encriptPassword(String contrasenia);


}
