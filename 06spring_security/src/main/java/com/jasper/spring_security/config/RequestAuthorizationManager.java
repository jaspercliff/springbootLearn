package com.jasper.spring_security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jasper.spring_security.domain.SysMenu;
import com.jasper.spring_security.mapper.SysMenuMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 动态权限  数据库修改了不用修改代码 通过url判断权限
  * disadvantage 每次请求都需要查询数据库  优化：将权限放到redis中 * 判断请求是否有权限访问
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RequestAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final SysMenuMapper menuMapper;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        log.info("uri============request uri=======================》:{}",uri);
        log.info("url============request url=======================》:{}",url);

        //不需要权限认证的请求
        if ("/auth/login".equals(uri) || "/error".equals(uri)){
            return new AuthorizationDecision(true);
        }

        //根据uri获取对应的权限 database
//        todo 优化：将权限放到redis中
        SysMenu sysMenu = menuMapper.selectOne(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getPath, uri.replaceFirst("/","")));
        if (sysMenu == null){
            return new AuthorizationDecision(false);
        }
        String permi = sysMenu.getPermi();
        log.info("permi===========url permission ========================》{}",permi);
        if (permi == null || permi.trim().isEmpty()){ //不需要权限认证 permi=""
            return new AuthorizationDecision(true);
        }


//        判断用户是否有权限
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        log.info("authorities=================user permission=================》{}",authorities);
        return new AuthorizationDecision(authorities.stream().anyMatch(
                grantedAuthority -> permi.equals(grantedAuthority.getAuthority())
        ));
    }
}
