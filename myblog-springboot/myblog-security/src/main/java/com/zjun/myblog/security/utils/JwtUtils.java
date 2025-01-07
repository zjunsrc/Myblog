package com.zjun.myblog.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * TODO
 *
 * @Author: zjun
 * @Date: 2025/1/6 11:04
 **/
@Component
public class JwtUtils implements InitializingBean {

    /**
     * 签发人
     */
    @Value("${jwt.issuer}")
    private String issuer;
    /**
     * 秘钥
     */
    private Key signingKey;

    /**
     * JWT 解析
     */
    private JwtParser jwtParser;
    /**
     * Token 失效时间（分钟）
     */
    @Value("${jwt.tokenExpireTime}")
    private Long tokenExpireTime;

    /**
     * 生成一个 Base64 的安全秘钥
     *
     * @return
     */
    private static String generateBase64SigningKey() {
        // 生成安全秘钥
        Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // 将密钥进行 Base64 编码
        String base64SigningKey = Base64.getEncoder().encodeToString(signingKey.getEncoded());

        return base64SigningKey;
    }

    public static void main(String[] args) {
        String key = generateBase64SigningKey();
        System.out.println("key: " + key);
    }

    /**
     * 解码配置文件中配置的 Base 64 编码 key 为秘钥
     *
     * @param base64SigningKey
     */
    @Value("${jwt.secret}")
    public void setSigningKey(String base64SigningKey) {
        signingKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64SigningKey));
    }

    /**
     * 解析 Token
     * @param token
     * @return
     */
    // public Jws<Claims> parseToken(String token) {
    //     try {
    //         return jwtParser.parseClaimsJws(token);
    //     } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
    //         throw new BadCredentialsException("Token 不可用", e);
    //     } catch (ExpiredJwtException e) {
    //         throw new CredentialsExpiredException("Token 失效", e);
    //     }
    // }

    /**
     * 初始化 JwtParser
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        jwtParser = Jwts.parserBuilder()
                .requireIssuer(issuer)
                .setSigningKey(signingKey)
                .setAllowedClockSkewSeconds(10)// 考虑到不同服务器之间可能存在时钟偏移，setAllowedClockSkewSeconds 用于设置能够容忍的最大的时钟误差
                .build();
    }

    /**
     * 生成 Token
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        // 设置 Token 失效时间
        LocalDateTime expireTime = now.plusMinutes(tokenExpireTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(signingKey)
                .compact();
    }

    /**
     * 校验 Token 是否可用
     *
     * @param token
     * @return
     */
    public void validateToken(String token) {
        jwtParser.parseClaimsJws(token);
    }

    /**
     * 解析 Token 获取用户名
     *
     * @param token
     * @return
     */
    public String getUsernameByToken(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

