package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM Turno t WHERE t.odontologo.id = :idOdontologo")
    List<Turno> findByOdonto(@Param("idOdontologo") Long idOdontologo);

    @Query("SELECT t FROM Turno t WHERE t.paciente.dni = :idPaciente")
    List<Turno> getTurnosPorPaciente(@Param("idPaciente") Long idPaciente);

    @Query("SELECT t FROM Turno t WHERE t.odontologo.username =:username")
    public List<Turno> getTurnosPorOdontologo(@Param("username") String username);

}
