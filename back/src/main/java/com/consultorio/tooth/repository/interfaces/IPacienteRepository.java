package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(value = "SELECT * FROM pacientes p JOIN usuarios u ON p.id_paciente = u.dni WHERE u.username =:username", nativeQuery = true)
    public Paciente findPacienteByUsername(@Param("username") String username);


    @Query("SELECT t.paciente FROM Turno t WHERE t.odontologo.dni =:idOdontologo")
    public List<Paciente> getPacientesPorOdontologo(@Param("idOdontologo") Long idOdontologo);


}
