package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecepcionistaRepository extends JpaRepository<Recepcionista, Long> {

}
