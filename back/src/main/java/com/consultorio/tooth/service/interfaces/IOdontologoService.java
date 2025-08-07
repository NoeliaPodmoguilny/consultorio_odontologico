package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Odontologo;
import java.util.List;

public interface IOdontologoService {

    public String encriptPassword(String contrasenia);

    public List<Odontologo> getOdontologos();

    public Odontologo findOdontologo(Long id);

    public Odontologo saveOdontologo(Odontologo odontologo);

    public void deleteOdontologo(Long id);

    public void updateOdontologo(Long id, Odontologo odontologo);

    public List<Odontologo> getOdontologosPorTratamiento(Long idTratamiento);

    public Odontologo findOdontologoByUsername(String username);

}
