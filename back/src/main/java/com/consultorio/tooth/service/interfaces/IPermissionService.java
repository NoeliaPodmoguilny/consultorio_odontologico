package com.consultorio.tooth.service.interfaces;

import com.consultorio.tooth.model.Permission;
import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    List<Object> findAllPermissions();

    Optional<Object> findPermissionById(Long id);

    Permission savePermission(Permission permission);

    void deletePermissionById(Long id);

    Permission updatePermission(Permission permission);

}
