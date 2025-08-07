package com.consultorio.tooth.service.implentations;

import com.consultorio.tooth.model.Permission;
import com.consultorio.tooth.repository.interfaces.IPermissionRepository;
import com.consultorio.tooth.service.interfaces.IPermissionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List findAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional findPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermissionById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

}
