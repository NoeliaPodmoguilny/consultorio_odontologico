
package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository  extends JpaRepository<Rol, Long>{
    
}
