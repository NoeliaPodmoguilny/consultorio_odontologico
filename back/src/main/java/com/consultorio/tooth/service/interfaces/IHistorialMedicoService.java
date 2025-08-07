package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.dto.historialMedico.HistorialMedicoDTO;
import com.consultorio.tooth.model.HistorialMedico;
import java.util.List;

public interface IHistorialMedicoService {

    public List<HistorialMedico> getHistorialMed();

    public HistorialMedico save(HistorialMedicoDTO historialmedicoDTO);

    public void deleteHistorialMed(Long id);

    public HistorialMedico findHistorialMed(Long id);

}
