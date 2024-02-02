package com.jasper.spring_security.filter;

import com.jasper.spring_security.domain.SysUser;
import com.jasper.spring_security.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 捕获请求头的Authorization 获取用户信息
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    @Override
    @SuppressWarnings({"rawtypes"})
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null){
//            放行 后面还有别的过滤器 添加到UsernamePasswordAuthenticationFilter之前了
            doFilter(request,response,filterChain);
            return;
        }
        Claims claims = jwtUtils.parseToken(token);
//        获取到用户信息需要将信息告诉springSecurity 来判断是否有权限 Authentication springContext
        String id = claims.get("id", String.class);
        String username = claims.get("username", String.class);
        String avatar = claims.get("avatar", String.class);
        List perms = claims.get("perms", List.class);

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Object perm : perms) {
            LinkedHashMap o = (LinkedHashMap) perm;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(o.get("authority").toString());
            grantedAuthorities.add(authority);
        }


        SysUser sysUser = new SysUser();
        sysUser.setId(id)
        .setUsername(username).setAvatar(avatar).
        setPerms(grantedAuthorities);
        log.debug("sysUser=========================================>{}",sysUser);

//        放到 security context中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser, null,sysUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        放行
        doFilter(request,response,filterChain);
    }
}
