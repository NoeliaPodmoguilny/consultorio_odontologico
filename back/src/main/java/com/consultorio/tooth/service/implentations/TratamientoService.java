package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.service.interfaces.ITratamientoService;
import com.consultorio.tooth.model.Tratamiento;
import com.consultorio.tooth.repository.interfaces.ITratamientoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TratamientoService implements ITratamientoService {

    @Autowired
    private ITratamientoRepository tratamientoRepo;

    @Override
    public List<Tratamiento> getTratamientos() {
        return tratamientoRepo.findAll();
    }

    @Override
    public Tratamiento saveTratamiento(Tratamiento tratam) {
       return tratamientoRepo.save(tratam);
    }

    @Override
    public void deleteTratamiento(Long id) {
        tratamientoRepo.deleteById(id);
    }

    @Override
    public Tratamiento findTratamiento(Long id) {
        return tratamientoRepo.findById(id).orElse(null);
    }

}
