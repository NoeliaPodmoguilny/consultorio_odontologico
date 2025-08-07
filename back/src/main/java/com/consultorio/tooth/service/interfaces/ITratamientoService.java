package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Tratamiento;
import java.util.List;

public interface ITratamientoService {

    public List<Tratamiento> getTratamientos();

    public Tratamiento saveTratamiento(Tratamiento tratam);

    public void deleteTratamiento(Long id);

    public Tratamiento findTratamiento(Long id);

}
