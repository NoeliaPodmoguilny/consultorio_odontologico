package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.model.Permission;
import com.consultorio.tooth.model.Rol;
import com.consultorio.tooth.repository.interfaces.IRolRepository;
import com.consultorio.tooth.service.interfaces.IRolService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PermissionService permissionService;

    // MÉTODOS
    @Override
    public List findAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Optional findRolById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol saveRol(Rol rol) {

        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        // Recuperar la Permission/s por su ID
        for (Permission per : rol.getPermissionList()) {
            readPermission = (Permission) permissionService.findPermissionById(per.getIdPermission()).orElse(null);

            if (readPermission != null) {
                permissionList.add(readPermission);
                System.out.println("Permiso agregado: " + readPermission.getPermissionName());
            } else {
                System.out.println("Permiso con ID " + per.getIdPermission() + " no encontrado");
            }
        }

        rol.setPermissionList(permissionList);

        Rol newRol = rolRepository.save(rol);
        return newRol;
    }

    @Override
    public void deleteRolById(Long id) {
        rolRepository.deleteById(id);
    }

}
