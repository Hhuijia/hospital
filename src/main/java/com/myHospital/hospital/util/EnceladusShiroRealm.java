package com.myHospital.hospital.util;

import com.myHospital.hospital.entity.Permission;
import com.myHospital.hospital.entity.Role;
import com.myHospital.hospital.entity.Users;
import com.myHospital.hospital.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/18/2019
 */
public class EnceladusShiroRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(EnceladusShiroRealm.class);

    @Autowired
    private UsersService usersService;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("################权限验证####################");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        String userIDNum = (String) principalCollection.getPrimaryPrincipal();
        Users users = (Users) principalCollection.getPrimaryPrincipal();
        List<Role> roles = usersService.findRoleByIDNum(users.getUserIDNum());

        for (Role role : roles){
            authorizationInfo.addRole(role.getRoleName());
            List<Permission> permissions = usersService.findPermissionByRoleId(role.getRoleId());
            for (Permission permission : permissions){
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        log.info("################身份验证####################");
        String userIDNum = (String) authenticationToken.getPrincipal();
        log.info("userIDNum:[{}]",userIDNum);
        Users users = Optional.ofNullable(usersService.findUserByIDNum(userIDNum)).orElseThrow(UnknownAccountException::new);
        log.info("userPassword:[{}]",users.getUserPwd());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(users, users.getUserPwd(), getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(users.getUserName()+users.getSalt()));
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", users);

        return authenticationInfo;
    }
}
