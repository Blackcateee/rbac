package com.zmxstudy.rbac.util;

import com.zmxstudy.rbac.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Jwt工具类
 *
 * @author star
 */
@Component
public class JwtUtil {
    /**
     * 有效期为，60 * 60 * 1000  一个小时
     */
    public static final Long JWT_TTL = SecurityConstant.TOKEN_EXPIRE * 1000L;

    /**
     * 设置秘钥明文 >= 32
     */
    public static final String JWT_KEY = "asdawdwadawfafewfswfeawfsdjkjhakjdhsfeasfawafwa";

    /**
     * 生成加密后的秘钥 secretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    /**
     * 生成 JWT
     */
    public String create(String subject, Long ttlMillis) {
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long nowMillis = System.currentTimeMillis();
        return Jwts.builder()
                .claims()
                //唯一的ID
                .id(UUID.randomUUID().toString())
                // 签发者
                .issuer("star")
                // 签发时间
                .issuedAt(new Date(nowMillis))
                // 过期时间
                .expiration(new Date(nowMillis + ttlMillis))
                // 主题 可以是JSON数据
                .subject(subject)
                .and()
                //使用HS256对称加密算法签名
                .signWith(generalKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * 生成 JWT
     */
    public String create(String subject) {
        return create(subject, null);
    }

    /**
     * 解析 JWT
     */
    public Claims parse(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
