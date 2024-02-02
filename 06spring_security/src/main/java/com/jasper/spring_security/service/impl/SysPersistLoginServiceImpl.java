package com.jasper.spring_security.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.spring_security.domain.SysPersistLogin;
import com.jasper.spring_security.mapper.SysPersistLoginMapper;
import com.jasper.spring_security.service.SysPersistLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author 21903
* @description 针对表【sys_persist_login】的数据库操作Service实现
* @createDate 2024-02-02 11:23:07
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class SysPersistLoginServiceImpl extends ServiceImpl<SysPersistLoginMapper, SysPersistLogin>
    implements SysPersistLoginService, PersistentTokenRepository {

    private final SysPersistLoginMapper sysPersistLoginMapper;

    /**
     * 选择rememberMe之后进入
     * @param token token
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        log.info("create new token =========================》{}",token);
        SysPersistLogin sysPersistLogin = new SysPersistLogin();
        sysPersistLogin.setSeries(token.getSeries());
        sysPersistLogin.setUsername(token.getUsername());
        sysPersistLogin.setToken(token.getTokenValue());
        sysPersistLogin.setLastUsed(token.getDate());
        sysPersistLoginMapper.insert(sysPersistLogin);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        LambdaUpdateWrapper<SysPersistLogin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysPersistLogin::getToken, tokenValue)
                .set(SysPersistLogin::getLastUsed, lastUsed)
                .eq(SysPersistLogin::getSeries, series);
        sysPersistLoginMapper.update(null, updateWrapper);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        SysPersistLogin sysPersistLogin = sysPersistLoginMapper.selectById(seriesId);
        return new PersistentRememberMeToken(sysPersistLogin.getUsername(), sysPersistLogin.getSeries(), sysPersistLogin.getToken(), sysPersistLogin.getLastUsed());
    }

    @Override
    public void removeUserTokens(String username){
        sysPersistLoginMapper.deleteByUsername(username);
    }
}




