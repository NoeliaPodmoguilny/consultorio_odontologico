
package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiagnosticoRepository extends  JpaRepository<Diagnostico, Long>{
    
}
