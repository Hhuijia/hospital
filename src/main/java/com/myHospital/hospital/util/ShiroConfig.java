package com.myHospital.hospital.util;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */

@Configuration
public class ShiroConfig {

    /**
     * authc：所有已登陆用户可访问
     * roles：有指定角色的用户可访问，通过[ ]指定具体角色，这里的角色名称与数据库中配置一致
     * perms：有指定权限的用户可访问，通过[ ]指定具体权限，这里的权限名称与数据库中配置一致
     * anon：所有用户可访问，通常作为指定页面的静态资源时使用
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        shiroFilterFactoryBean.setLoginUrl("/common/notLogin");//未登录界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/notRole"); //无权限界面
//        shiroFilterFactoryBean.setSuccessUrl("/home/index"); //登陆成功后跳转的界面

        filterChainDefinitionMap.put("/guest/**", "anon"); //游客身份
        filterChainDefinitionMap.put("/user/**", "roles[user]"); //用户身份，需要角色权限 “user”
        filterChainDefinitionMap.put("/doctor/**", "roles[doctor]"); //医生身份，需要角色权限 “doctor”
        filterChainDefinitionMap.put("/nurse/**", "roles[nurse]"); //护士身份，需要角色权限 “nurse”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]"); //管理员身份，需要角色权限 “admin”
        filterChainDefinitionMap.put("/common/login", "anon"); //开放登录窗口
        filterChainDefinitionMap.put("/**","authc"); //其余窗口一律拦截，主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        System.out.println("Shiro拦截器工厂类注入成功");

        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);//散列算法
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);//散列次数
        return hashedCredentialsMatcher;
    }

    @Bean
    public EnceladusShiroRealm enceladusShiroRealm(){
        EnceladusShiroRealm enceladusShiroRealm = new EnceladusShiroRealm();
        enceladusShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return enceladusShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(enceladusShiroRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public PasswordHelper passwordHelper(){
        return new PasswordHelper();
    }
}
