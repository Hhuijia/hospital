package com.myHospital.hospital.util;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Queeney.Huang
 * @Date: 1/21/2019
 */

@Configuration
public class ShiroConfig {

    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean(name = "ehCacheManager")
    public EhCacheManager getEhCacheManager() {
        log.info("##################getEhCacheManager##################");
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        log.info("##################getLifecycleBeanPostProcessor##################");
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public PasswordHelper passwordHelper(){
        return new PasswordHelper();
    }

    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        log.info("##################hashedCredentialsMatcher加密器##################");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);//散列算法
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);//散列次数
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        log.info("##################getDefaultAdvisorAutoProxyCreator##################");
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean
    public EnceladusShiroRealm getEnceladusShiroRealm(EhCacheManager ehCacheManager){
        log.info("##################enceladusShiroRealm##################");
        EnceladusShiroRealm enceladusShiroRealm = new EnceladusShiroRealm();
//        enceladusShiroRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        enceladusShiroRealm.setCacheManager(ehCacheManager);
        return enceladusShiroRealm;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(EnceladusShiroRealm enceladusShiroRealm) {
        log.info("##################getDefaultWebSecurityManager##################");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(enceladusShiroRealm);
        // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        log.info("##################getAuthorizationAttributeSourceAdvisor##################");
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    /**
     * 添加ShiroDialect 为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        log.info("##################shiroDialect##################");
        return new ShiroDialect();
    }

    /**
     * authc：所有已登陆用户可访问
     * roles：有指定角色的用户可访问，通过[ ]指定具体角色，这里的角色名称与数据库中配置一致
     * perms：有指定权限的用户可访问，通过[ ]指定具体权限，这里的权限名称与数据库中配置一致
     * anon：所有用户可访问，通常作为指定页面的静态资源时使用
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        log.info("##################shiroFilterFactoryBean##################");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        shiroFilterFactoryBean.setLoginUrl("/common/login");//未登录界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/notRole"); //无权限界面
        shiroFilterFactoryBean.setSuccessUrl("/common/index"); //登陆成功后跳转的界面

        loadShiroFilterChain(shiroFilterFactoryBean);

        return shiroFilterFactoryBean;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/guest/**", "anon"); //游客身份
        filterChainDefinitionMap.put("/user/**", "roles[user]"); //用户身份，需要角色权限 “user”
        filterChainDefinitionMap.put("/doctor/**", "roles[doctor]"); //医生身份，需要角色权限 “doctor”
        filterChainDefinitionMap.put("/nurse/**", "roles[nurse]"); //护士身份，需要角色权限 “nurse”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]"); //管理员身份，需要角色权限 “admin”
        filterChainDefinitionMap.put("/common/login", "anon"); //开放登录窗口

        filterChainDefinitionMap.put("/static/**", "anon");

        log.info("##################从数据库读取权限规则，加载到shiroFilter中##################");

//        Map<String, String> permissions = new LinkedHashMap<>();
//        permissions.put("/users/find", "perms[user:find]");
//        filterChainDefinitionMap.putAll(permissions);

        filterChainDefinitionMap.put("/**","authc"); //其余窗口一律拦截，主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }
}
