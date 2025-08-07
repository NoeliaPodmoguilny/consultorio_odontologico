package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.service.interfaces.IUsuarioService;
import com.consultorio.tooth.model.Usuario;
import com.consultorio.tooth.repository.interfaces.IUsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    // Traer todos los usuarios
    @Override
    public List getUsuarios() {
        return usuarioRepo.findAll();
    }

    // Obtener usuario por dni
    @Override
    public Optional getUsuarioByDni(Long id) {
        return usuarioRepo.findById(id);
    }

    // encriptar contraseña
    @Override
    public String encriptPassword(String contrasenia) {
        return new BCryptPasswordEncoder().encode(contrasenia);
    }

    // Traer usuario por username
    @Override
    public Optional<Usuario> findUsuByUsername(String username) {
        return usuarioRepo.findUserEntityByUsername(username);
    }


}
