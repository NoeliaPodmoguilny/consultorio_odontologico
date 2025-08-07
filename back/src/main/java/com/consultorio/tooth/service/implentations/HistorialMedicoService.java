package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.dto.historialMedico.HistorialMedicoDTO;
import com.consultorio.tooth.model.Diagnostico;
import com.consultorio.tooth.model.HistorialMedico;
import com.consultorio.tooth.model.Paciente;
import com.consultorio.tooth.model.Tratamiento;
import com.consultorio.tooth.repository.interfaces.IDiagnosticoRepository;
import com.consultorio.tooth.repository.interfaces.IHisorialMedicoRepository;
import com.consultorio.tooth.repository.interfaces.IPacienteRepository;
import com.consultorio.tooth.service.interfaces.IHistorialMedicoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialMedicoService implements IHistorialMedicoService {

    @Autowired
    private IHisorialMedicoRepository historialMedRepo;
    @Autowired
    private IPacienteRepository pacienteRepo;
    @Autowired
    private IDiagnosticoRepository diagnosticoRepo;

    @Override
    public List<HistorialMedico> getHistorialMed() {
        return historialMedRepo.findAll();
    }

    @Override
    public HistorialMedico save(HistorialMedicoDTO historialmedicoDTO) {

        // Busco el paciente por dni 
        Paciente paci = pacienteRepo.findById(historialmedicoDTO.getPacienteDNI()).orElse(null);
        System.out.println("Paciente por DNI: " + paci);
        // creo un nuevo objeto
        HistorialMedico newHistMed = new HistorialMedico();

        // seteo los datos del nuevo obj con los del historialMedicoDTO
        newHistMed.setPaciente(paci);
        newHistMed.setCalle(historialmedicoDTO.getCalle());
        newHistMed.setAltura(historialmedicoDTO.getAltura());
        newHistMed.setCiudad(historialmedicoDTO.getCiudad());
        newHistMed.setProvincia(historialmedicoDTO.getProvincia());
        newHistMed.setAlturaCm(historialmedicoDTO.getAlturaCm());
        newHistMed.setPeso(historialmedicoDTO.getPeso());
        newHistMed.setEdad(historialmedicoDTO.getEdad());
        newHistMed.setEstadoSalud(historialmedicoDTO.getEstadoSalud());
        newHistMed.setCondiciones(historialmedicoDTO.getCondiciones());
        newHistMed.setComentarios(historialmedicoDTO.getComentarios());
        newHistMed.setContactoNombre(historialmedicoDTO.getContactoNombre());
        newHistMed.setContactoRelacionConPaciente(historialmedicoDTO.getContactoRelacionConPaciente());
        newHistMed.setContactoTelefono(historialmedicoDTO.getContactoTelefono());
        newHistMed.setEmbarazo(historialmedicoDTO.getEmbarazo());
        newHistMed.setFechaIngreso(historialmedicoDTO.getFechaIngreso());
        newHistMed.setGenero(historialmedicoDTO.getGenero());
        newHistMed.setHabitos(historialmedicoDTO.getHabitos());
        newHistMed.setHistorialFamiliar(historialmedicoDTO.getHistorialFamiliar());
        newHistMed.setMedicamentos(historialmedicoDTO.getMedicamentos());
        newHistMed.setOtrosHabitos(historialmedicoDTO.getOtrosHabitos());
        newHistMed.setSeguroMedico(historialmedicoDTO.getSeguroMedico());
        newHistMed.setSeguroMedicoDescripcion(historialmedicoDTO.getSeguroMedicoDescripcion());
        newHistMed.setOtrosHistorialFamiliar(historialmedicoDTO.getOtrosHistorialFamiliar());

        // Diagnostico
        // Creo un obj diagnostico nuevo y setep los valores que trae el historialMedicoDTO
        Diagnostico newDiagnostico = new Diagnostico();
        newDiagnostico.setTipoDiagnostico(historialmedicoDTO.getTipoDiagnostico());
        newDiagnostico.setFechaDiagnostico(historialmedicoDTO.getFechaDiagnostico());
        // creo una lista y le agrego los datos de la lista que traigo del front
        List<Tratamiento> newListaTratamientos = new ArrayList<>();
        for (Tratamiento tratamiento : historialmedicoDTO.getTratamientos()) {
            newListaTratamientos.add(tratamiento);
        }
        System.out.println("tratamientos: " + newListaTratamientos);
        // agrego la nueva lista al obj diagnostico
        newDiagnostico.setTratamientos(newListaTratamientos);
        // Llamo al service de diagnostico para guardar en la BD
        Diagnostico diagnosticoHM = diagnosticoRepo.save(newDiagnostico);
        // seteo el diagnostico al historial medico
        newHistMed.setDiagnostico(diagnosticoHM);

        return historialMedRepo.save(newHistMed);
    }

    @Override
    public void deleteHistorialMed(Long id) {
        historialMedRepo.deleteById(id);
    }

    @Override
    public HistorialMedico findHistorialMed(Long id) {
        return historialMedRepo.findByPaciente(id);
    }

}
