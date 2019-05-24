package com.x.mall.common.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于生成和解析JWT token的工具类
 *
 *JWT token的格式：header.payload.signature
 *
 * header的格式（算法、token的类型）： {"alg":"HS512","typ":"JWT"}
 *
 * payload的格式（用户名、创建时间、生成时间）： {"sub":"wang","created":148079981393,"exp":1489684781}
 *
 * signature的生成算法： HMACSHA512(base64UrlEncode(header)+"."+base64UrlEncode(payload),secret)
 *
 */
@Component
public class JwtTokenUtil {

    private static final Logger LOGGER= LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLIAIM_KEY_USERNAME="sub";
    private static final String CLIAIM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token){
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
           LOGGER.info("JWT格式验证失败：{}",token);
        }
        return claims;
    }


    /**
     * 从token中获取登陆用户名
     */
    public String getUserNameFromToken(String token){
        String userName;
        try {
            Claims claims= getClaimsFromToken(token);
            userName=claims.getSubject();
        } catch (Exception e) {
            userName=null;
        }
        return userName;
    }


    /**
     * 验证token是否还有效
     * @param token 客户端传入的token
     * @param userDetails  从数据库中查询出来的用户数据
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String userName=getUserNameFromToken(token);
        return userName.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */

    private Date getExpiredDateFromToken(String token){
        Claims claims=getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLIAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLIAIM_KEY_CREATED,new Date());
        return  generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新  (没过期可以被刷新！？)
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token){
        Claims claims=getClaimsFromToken(token);
        claims.put(CLIAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


}
