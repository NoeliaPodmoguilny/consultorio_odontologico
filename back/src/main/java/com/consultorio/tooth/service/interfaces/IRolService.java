package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Rol;
import java.util.List;
import java.util.Optional;

public interface IRolService {

    public List findAllRoles();

    public Optional<Rol> findRolById(Long id);

    public Rol saveRol(Rol rol);

    public void deleteRolById(Long id);

   


}
