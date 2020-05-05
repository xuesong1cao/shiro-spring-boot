package com.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager WebSecurity){

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilter.setSecurityManager(WebSecurity);

//        anon: 无需认证就可访问
//        authc：必须认证了，才可以访问
//        user：必须拥有了‘记住我’功能才能用
//        perms：拥有对某个资源的权限才能访问
//        role：拥有某个角色权限才可以访问

        Map<String, String> definitionMap = new LinkedHashMap<>();

        //授权
        //拥有manager:add这个权限的角色才能通过/add请求访问对应页面
        //想拥有该权限，就需要在realm中进行授权
//        definitionMap.put("/add", "perms[manager:add]");
        definitionMap.put("/add", "authc");
        //设置访问权限
        definitionMap.put("/update", "perms[manager:update]");

        shiroFilter.setFilterChainDefinitionMap(definitionMap);

        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/index");

        //设置未授权请求，如果未授权则发送该请求访问对应页面
        shiroFilter.setUnauthorizedUrl("/unauthu");

        return shiroFilter;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联Realm
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect 用于整合thymeleaf和shiro
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
