package com.myHospital.hospital.util;

import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/18/2019
 */
public class EnceladusShiroRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userIDNum = (String) principalCollection.getPrimaryPrincipal();
        Users user = usersService.findUserByIDNum(userIDNum);

        for (Role role : user.getRole()){
            authorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()){
                authorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     * @param authenticationToken 用户身份信息的token
     * @return 封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证————");
        String userIDNum = (String) authenticationToken.getPrincipal();
        Users users = usersService.findUserByIDNum(userIDNum);
        if (users == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(users.getUserIDNum(),
                users.getUserPwd(), ByteSource.Util.bytes(users.getUserIDNum()), getName());
        return authenticationInfo;
    }
}
