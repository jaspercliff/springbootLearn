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

    public  String generateToken(Map<String,Object> map){
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


    public  Claims parseToken(String token){
        return Jwts.parser().verifyWith(KEY)
                .build().parseSignedClaims(token)
                .getPayload();
    }

//    // 生成 Token，添加用户最后操作时间戳
//    public static String generateTokenWithLastActivity(Map<String, Object> map) {
//        // 添加用户最后操作时间戳到 Claims
//        LocalDateTime lastActivityTime = LocalDateTime.now();
//        map.put("lastActivity", lastActivityTime);
//
//        return Jwts.builder()
//                // ... (其他构建 Token 的代码)
//                .setClaims(map)
//                .signWith(KEY, Jwts.SIG_HMACSHA256)
//                .compact();
//    }
//
//    // 刷新 Token，检查用户最后操作时间戳
//    public static String refreshToken(String token) {
//        Claims claims = parseToken(token);
//
//        // 获取用户最后操作时间戳
//        LocalDateTime lastActivityTime = LocalDateTime.parse(claims.get("lastActivity", String.class));
//
//        // 检查是否可以刷新 Token，例如检查用户最后操作时间是否在10分钟内
//        if (canRefreshToken(lastActivityTime)) {
//            // 生成新的 Token，更新过期时间等信息
//            Map<String, Object> newClaims = /* 从原有 claims 中获取需要的信息 */;
//            String newToken = generateToken(newClaims);
//
//            return newToken;
//        } else {
//            // 无法刷新 Token，可能用户最后操作时间已经超过10分钟或其他原因
//            // 返回 null 或其他标识
//            return null;
//        }
//    }
//
//    // 检查是否可以刷新 Token 的方法，可以根据需要进行实现
//    private static boolean canRefreshToken(LocalDateTime lastActivityTime) {
//        // 在这里添加你的逻辑，例如检查用户最后操作时间是否在10分钟内
//        LocalDateTime now = LocalDateTime.now();
//        Duration duration = Duration.between(lastActivityTime, now);
//
//        return duration.toMinutes() <= 10;
//    }

}
