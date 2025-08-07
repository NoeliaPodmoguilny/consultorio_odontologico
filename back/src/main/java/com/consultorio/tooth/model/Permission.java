package com.consultorio.tooth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;
    @Column(unique = true, nullable = false)
    private String permissionName;

    public Permission() {
    }

    public Permission(Long idPermission, String permissionName) {
        this.idPermission = idPermission;
        this.permissionName = permissionName;
    }

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }



}
