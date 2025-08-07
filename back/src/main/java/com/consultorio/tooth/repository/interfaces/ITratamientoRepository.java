
package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITratamientoRepository extends JpaRepository<Tratamiento, Long>{
    
}
