
package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IHisorialMedicoRepository extends JpaRepository<HistorialMedico, Long>{

    @Query("SELECT hm FROM HistorialMedico hm WHERE hm.paciente.dni=:idPaciente")
    public HistorialMedico findByPaciente(@Param("idPaciente") Long id);
    
}
