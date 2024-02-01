package com.jasper.spring_security.service.impl;

import com.jasper.spring_security.domain.SysUser;
import com.jasper.spring_security.mapper.SysRoleMapper;
import com.jasper.spring_security.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class SysUserDetailServiceImpl implements UserDetailsService {

    private final SysUserMapper userMapper;
    private final SysRoleMapper roleMapper;
    /**
     * 根据用户名查询用户
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.getUserByUsername(username);
        String permsStr =  roleMapper.getPerms(sysUser.getRoleIds());
        List<GrantedAuthority> authorities = Arrays.stream(permsStr.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        sysUser.setPerms(authorities);
        return sysUser;
    }
}
