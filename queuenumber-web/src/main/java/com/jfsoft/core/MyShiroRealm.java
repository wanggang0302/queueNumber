package com.jfsoft.core;

import com.jfsoft.model.SysRole;
import com.jfsoft.model.SysUser;
import com.jfsoft.user.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * wanggang
 * 2017-08-10 17:22
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等
     * 权限认证，为当前登录的Subject授予角色和权限
     * @see ：本例中该方法的调用时机为需授权资源被访问时
     * @see ：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        /*
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        logger.debug("##################执行Shiro权限认证##################");

        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);

        //到数据库查是否有此对象
        try {

            // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
            SysUser user = sysUserService.getByUsername(loginName);

            if(null != user){

                //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                //用户的角色集合
                info.setRoles(user.getRolesName());
                //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
                List<SysRole> roleList = user.getRoleList();
                for (SysRole role : roleList) {
                    info.addStringPermissions(role.getPermissionsName());
                }
                // 或者按下面这样添加
                //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
                //simpleAuthorInfo.addRole("admin");
                //添加权限
                //simpleAuthorInfo.addStringPermission("admin:manage");
                //logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
                return info;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 登录认证
     *
     * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法
     *
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //logger.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));

        try {

            //查出是否有此用户
            String username = token.getUsername();
            logger.debug("authentication username is {}.", username);
            //通过username从数据库中查找 User对象，如果找到，没找到.
            //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
            SysUser user = sysUserService.getByUsername(username);

            if(user!=null){
                // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
                return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
