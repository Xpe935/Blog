package com.icarus.shiro;

import com.icarus.entity.MUser;
import com.icarus.service.MUserService;
import com.icarus.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken =  (JwtToken) authenticationToken;
        //获取用户id
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        MUser user = userService.getById(Long.valueOf(userId));
        if(user == null){
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus() == -1){
            throw new LockedAccountException("账户不存在");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);//将user内容转移到profile中
        //返回用户信息  密钥 token 用户名字
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
