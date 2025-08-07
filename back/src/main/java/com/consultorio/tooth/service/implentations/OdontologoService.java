package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.service.interfaces.IOdontologoService;
import com.consultorio.tooth.model.Odontologo;
import com.consultorio.tooth.model.Rol;
import com.consultorio.tooth.model.Tratamiento;
import com.consultorio.tooth.repository.interfaces.IOdontologoRepository;
import com.consultorio.tooth.service.interfaces.IRolService;
import com.consultorio.tooth.service.interfaces.ITratamientoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService implements IOdontologoService {

    // INYECCIÓN DE DEPENDENCIAS
    @Autowired
    private IOdontologoRepository odontologoRepo;

    @Autowired
    private IRolService rolServ;

    @Autowired
    private ITratamientoService tratamientoServ;

    // MÉTODOS
    @Override
    public String encriptPassword(String contrasenia) {
        return new BCryptPasswordEncoder().encode(contrasenia);
    }

    @Override
    public List<Odontologo> getOdontologos() {
        return odontologoRepo.findAll();
    }

    @Override
    public Odontologo findOdontologo(Long id) {
        return odontologoRepo.findById(id).orElse(null);
    }
    
    @Override
    public Odontologo findOdontologoByUsername(String username) {
        return odontologoRepo.findOdontologoByUsername(username);
    }

    @Override
    public Odontologo saveOdontologo(Odontologo odontologo) {

        // ENCRIPTAR CONTRASEÑA
        odontologo.setContrasenia(this.encriptPassword(odontologo.getContrasenia()));

        // Recuperar el ROL por su ID
        Optional<Rol> optionalRol = rolServ.findRolById(odontologo.getRol().getIdRol());
        optionalRol.ifPresent(odontologo::setRol);  // Solo asignamos el rol si existe

        // Recuperar los TRATAMIENTOS por su ID 
        List<Tratamiento> tratamientoList = new ArrayList<>();

        // Evitamos NullPointerException verificando si la lista de tratamientos es nula
        List<Tratamiento> tratamientosOdonto = odontologo.getTratamiento();
        if (tratamientosOdonto != null) {
            for (Tratamiento trat : tratamientosOdonto) {
                Tratamiento currentTratamiento = tratamientoServ.findTratamiento(trat.getIdTratamiento());
                if (currentTratamiento != null) {
                    tratamientoList.add(currentTratamiento);
                }
            }
        }
        // Setear la lista nueva de tratamientos al objeto odontologo
        odontologo.setTratamiento(tratamientoList);

        // Guardar el nuevo odontologo con su contraseña encriptada, rol, tratamientos y jornadas
        Odontologo newOdonto = odontologoRepo.save(odontologo);
        return newOdonto;
    }

    @Override
    public void deleteOdontologo(Long id) {
        odontologoRepo.deleteById(id);
    }

    @Override
    public void updateOdontologo(Long id, Odontologo odonto) {

        Odontologo odontologo = this.findOdontologo(id);

        odontologo.setUsername(odonto.getUsername());
        odontologo.setContrasenia(odonto.getContrasenia());
        odontologo.setNombre(odonto.getNombre());
        odontologo.setApellido(odonto.getApellido());
        odontologo.setFechaNacimiento(odonto.getFechaNacimiento());
        odontologo.setTelefono(odonto.getTelefono());
        odontologo.setCorreoElectronico(odonto.getCorreoElectronico());

        odontologoRepo.save(odontologo);
    }

    // DEVUELVE LA LISTA DE ODONTOLOGOS PARA EL TRATAMIENTO SELECCIONADO
    @Override
    public List<Odontologo> getOdontologosPorTratamiento(Long idTratamiento) {
        return odontologoRepo.getOdontologosPorTratamiento(idTratamiento);
    }



}
