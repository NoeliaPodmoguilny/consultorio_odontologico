
package com.consultorio.tooth.repository.interfaces;

import com.consultorio.tooth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPermissionRepository extends JpaRepository<Permission, Long>{
    
}
