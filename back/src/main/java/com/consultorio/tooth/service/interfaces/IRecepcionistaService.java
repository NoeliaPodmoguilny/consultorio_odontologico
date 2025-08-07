package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Recepcionista;
import java.util.List;

public interface IRecepcionistaService {

    public String encriptPassword(String contrasenia);

    public List<Recepcionista> getRecepcionistas();

    public Recepcionista findRecepcionista(Long id);

    public void deleteRecepcionista(Long id);

    public Recepcionista saveRecepcionista(Recepcionista recep);

    public void updateRecepcionista(Long id, Recepcionista recep);

}
