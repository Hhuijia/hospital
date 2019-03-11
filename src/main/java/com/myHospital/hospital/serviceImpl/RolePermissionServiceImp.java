package com.myHospital.hospital.serviceImpl;

import com.myHospital.hospital.dao.RolePermissionDao;
import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.RolePermission;
import com.myHospital.hospital.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

/**
 * @author QUEENEY
 * @date 2019/3/10
 */
@Repository
public class RolePermissionServiceImp implements RolePermissionService {
    private static final Logger log = LoggerFactory.getLogger(RolePermissionServiceImp.class);

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public void addRole(Role role, List<String> permissionIds) {
        log.info("******************addRole********************");
        String strRole = String.format("%04d", new Random().nextInt(1001));
        role.setRoleId( "ROLE_" + strRole + "_" + System.currentTimeMillis());
        rolePermissionDao.addRole(role);
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            String strRolePer = String.format("%04d", new Random().nextInt(1001));
            rolePermission.setRpId( "ROLEPER_" + strRolePer + "_" + System.currentTimeMillis());
            rolePermission.setRoleId(role.getRoleId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionDao.addRolePermission(rolePermission);
        }
    }

    @Override
    public void addPermission(Permission permission) {
        log.info("******************addPermission********************");
        String strPermission = String.format("%04d", new Random().nextInt(1001));
        permission.setPermissionId( "PERMISSION" + strPermission + "_" + System.currentTimeMillis());
        rolePermissionDao.addPermission(permission);
    }

    @Override
    public List<Permission> findAllPermission() {
        log.info("******************findAllPermission********************");
        return rolePermissionDao.findAllPermission();
    }

    @Override
    public List<Role> findAllRole() {
        log.info("******************findAllRole********************");
        List<Role> roles = rolePermissionDao.findAllRole();
        for (Role role : roles){
            StringBuilder str = new StringBuilder();
            List<RolePermission> rolePermissions = role.getRolePermissions();
            if (rolePermissions.size()==0){
                role.setPerNameContain(" ");
            }else {
                for (RolePermission rolePermission : rolePermissions) {
                    String permissionName = rolePermissionDao.findPerNameByPerId(rolePermission.getPermissionId());
                    str.append(permissionName).append(",");
                }
                role.setPerNameContain(str.toString());
            }
        }
        return roles;
    }

    @Override
    public void delRole(String roleId) {
        log.info("******************delRole********************");
        rolePermissionDao.deleteRPByRoleId(roleId);
        rolePermissionDao.deleteURByRoleId(roleId);
        rolePermissionDao.deleteRoleById(roleId);
    }

    @Override
    public void delPermission(String permissionId) {
        log.info("******************delPermission********************");
        rolePermissionDao.deleteRPByPermissionId(permissionId);
        rolePermissionDao.deletePermissionById(permissionId);
    }
}
