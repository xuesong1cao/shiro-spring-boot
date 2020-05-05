package com.config;

import org.apache.catalina.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm{

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前用户对象，需要在认证方法中进行传递
        Subject subject = SecurityUtils.getSubject();
//        Manager currentUser = (Manager)subject.getPrincipal();
        info.addStringPermission("manager:update");
        //设置当前用户权限，一般是从表中取出，因为管理员表一般有权限属性
//        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 用户名和密码，这里是写死的，也可以整合mybaties来进行
        String username = "admin";
        String password = "1234";

        // 取出token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        Manager manager = managerService.getManager(token.getUsername());

        // 用户名认证
        if(!token.getUsername().equals(username)){
            return null;    //返回null就会在controller层抛出UnknownAccountException
        }
        // 密码认证
        return new SimpleAuthenticationInfo("", password, "");
    }
}
