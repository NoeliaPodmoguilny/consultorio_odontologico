package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.service.interfaces.IRecepcionistaService;
import com.consultorio.tooth.model.Recepcionista;
import com.consultorio.tooth.model.Rol;
import com.consultorio.tooth.repository.interfaces.IRecepcionistaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService implements IRecepcionistaService {

    @Autowired
    private IRecepcionistaRepository recepRepo;

    @Autowired
    private RolService rolServ;

    // ENCRIPTAR CONTRASEÑA
    @Override
    public String encriptPassword(String contrasenia) {
        return new BCryptPasswordEncoder().encode(contrasenia);
    }

    // DEVUELVE UNA LISTA CON TODOS LOS REGISTROS
    @Override
    public List<Recepcionista> getRecepcionistas() {
        return recepRepo.findAll();
    }

    // BUSAR POR DNI (id)
    @Override
    public Recepcionista findRecepcionista(Long id) {
        return recepRepo.findById(id).orElse(null);
    }

    // ELIMINAR UN REGISTRO POR ID
    @Override
    public void deleteRecepcionista(Long id) {
        recepRepo.deleteById(id);
    }

    // GUARDAR UN NUEVO REGISTRO
    @Override
    public Recepcionista saveRecepcionista(Recepcionista recepcionista) {

        // ENCRIPTAR CONTRASEÑA
        recepcionista.setContrasenia(this.encriptPassword(recepcionista.getContrasenia()));

        // Recuperar el Rol por su ID 
        Optional<Rol> optionalRol = rolServ.findRolById(recepcionista.getRol().getIdRol());
        optionalRol.ifPresent(recepcionista::setRol);

        // Guardamos el nuevo registro
        Recepcionista newrecep = recepRepo.save(recepcionista);
        return newrecep;
    }

    // ACTUALIZAR UN REGISTRO
    @Override
    public void updateRecepcionista(Long id, Recepcionista recep) {

        Recepcionista recepcionista = this.findRecepcionista(id);

        recepcionista.setUsername(recep.getUsername());
        recepcionista.setContrasenia(recep.getContrasenia());
        recepcionista.setNombre(recep.getNombre());
        recepcionista.setApellido(recep.getApellido());
        recepcionista.setFechaNacimiento(recep.getFechaNacimiento());
        recepcionista.setTelefono(recep.getTelefono());
        recepcionista.setCorreoElectronico(recep.getCorreoElectronico());

        recepRepo.save(recepcionista);
    }

}
