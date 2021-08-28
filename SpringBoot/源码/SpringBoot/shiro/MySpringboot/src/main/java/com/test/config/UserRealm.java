package com.test.config;

import com.test.pojo.User;
import com.test.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权o");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //获取当前登录用户
        Subject subject =SecurityUtils.getSubject();
        User CurrentUser=(User)subject.getPrincipal();
        //从数据库中获取用户对应的权限
        info.addStringPermission(CurrentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证的o");

        // 用户名,密码到数据库中取
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        User user=userService.selectUserByName(userToken.getUsername());
        if(user==null){  //用户不存在
            return null; //抛出异常 UnknownAccountException
        }

        //密码认证:shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

}