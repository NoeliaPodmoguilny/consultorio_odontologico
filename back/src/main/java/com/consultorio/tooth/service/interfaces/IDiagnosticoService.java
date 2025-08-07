
package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Diagnostico;
import java.util.List;


public interface IDiagnosticoService {

    public List<Diagnostico> getDiagnosticos();

    public Diagnostico saveDiagnostico(Diagnostico diagnostico);

    public void deleteDiagnostico(Long id);

    public Diagnostico findDiagnostico(Long id);
    
}
