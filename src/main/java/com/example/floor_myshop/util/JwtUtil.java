package com.example.floor_myshop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {
    private String key;//密钥

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private long ttl;//一个小时

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }



    /**
     * 创建JWT
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJWT(String id, String subject, String roles,Integer uid){
        long nowMillis = System.currentTimeMillis();
        Date exp=new Date(nowMillis+ttl);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(exp)
                .claim("roles", roles)
                .claim("uid", uid);
        if(ttl>0){
            builder.setExpiration(exp);
        }
        return builder.compact();
    }



    /**
     * 解析JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }
}
