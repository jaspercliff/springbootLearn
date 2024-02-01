package com.jasper.spring_security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {
    private final static String SECRET = "ajkshfajkshfljashfkaasdfdjkghjkshgjkfhdjkghdfjk";
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(Map<String,Object> map){
        return Jwts.builder()
                .header()
                .add("alg","HS256")
                .add("typ","JWT")
                .and()
                .claims()
                .add(map)
                .issuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).toInstant()))
                .expiration(Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.of("Asia/Shanghai")).toInstant()))
                .and().
                signWith(KEY,Jwts.SIG.HS256)
                .compact();
    }


    public static Claims parseToken(String token){
        return Jwts.parser().verifyWith(KEY)
                .build().parseSignedClaims(token)
                .getPayload();
    }
}
