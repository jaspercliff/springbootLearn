package com.jasper.spring_security.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {

    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final static String SECRET = "ajkshfajkshfljashfkaasdfdjkghjkshgjkfhdjkghdfjk";
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public  String generateToken(Map<String,Object> map){
        return Jwts.builder()
                .header()
                .add("alg","HS256")
                .add("typ","JWT")
                .and()
                .claims()
                .add(map)
                .issuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).toInstant()))
                .expiration(Date.from(LocalDateTime.now().plusMinutes(expiration).atZone(ZoneId.of("Asia/Shanghai")).toInstant()))
                .and().
                signWith(KEY,Jwts.SIG.HS256)
                .compact();
    }


    public  Claims parseToken(String token){
        token = token.substring(tokenHead.length());
        return Jwts.parser().verifyWith(KEY)
                .build().parseSignedClaims(token)
                .getPayload();
    }
    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration();
    }
    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    public String refreshHeadToken(String oldToken) {
        if(StrUtil.isEmpty(oldToken)){
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if(StrUtil.isEmpty(token)){
            return null;
        }
        //token校验不通过
        Claims claims = parseToken(token);
        if(claims==null){
            return null;
        }
        //如果token已经过期，不支持刷新
        if(isTokenExpired(token)){
            return null;
        }
        //如果token在30分钟之内刚刷新过，返回原token
        if(tokenRefreshJustBefore(token)){
            return token;
        }else{
            claims.put("iat", new Date());
            return generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token) {
        Claims claims = parseToken(token);
        Instant created = Instant.ofEpochMilli(claims.get("iat", Long.class));
        Instant refreshDate = Instant.now();

        Instant expirationDate = created.plusSeconds(30*60);
        return refreshDate.isAfter(created) && refreshDate.isBefore(expirationDate);
    }

}
