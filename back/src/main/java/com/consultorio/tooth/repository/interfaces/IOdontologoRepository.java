package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Odontologo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("SELECT o FROM Odontologo o "
            + "JOIN o.tratamiento t "
            + "WHERE t.idTratamiento = :idTratamiento")
    List<Odontologo> getOdontologosPorTratamiento(@Param("idTratamiento") Long idTratamiento);

    @Query(value = "SELECT * FROM odontologos o JOIN usuarios u ON o.id_odontologo = u.dni WHERE u.username = :username", nativeQuery = true)
    public Odontologo findOdontologoByUsername(String username);

}
