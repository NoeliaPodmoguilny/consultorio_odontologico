
package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.model.Diagnostico;
import com.consultorio.tooth.repository.interfaces.IDiagnosticoRepository;
import com.consultorio.tooth.service.interfaces.IDiagnosticoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoService implements IDiagnosticoService{

    @Autowired
    private IDiagnosticoRepository diagnosticoRepo;
    
    @Override
    public List<Diagnostico> getDiagnosticos() {
        return diagnosticoRepo.findAll();
    }

    @Override
    public Diagnostico saveDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepo.save(diagnostico);
    }

    @Override
    public void deleteDiagnostico(Long id) {
        diagnosticoRepo.deleteById(id);
    }

    @Override
    public Diagnostico findDiagnostico(Long id) {
        return diagnosticoRepo.findById(id).orElse(null);
    }
    
}
