package com.leyiju.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with com.leyiju.utils.
 *
 * @author: Xavier
 * @time: 2019/6/6 17:05
 */
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final long EXPIRATION_TIME = 432000000;

    private static final String SECRET = "secret";

    /**
     * 根据用户生成token
     * @param userDetails 用户信息
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return Jwts.builder().setClaims(claims).setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 根据数据生成token
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put("created", new Date());
        return generateToken(claims);
    }

    /**
     * 解析JWT
     *
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 验证JWT
     *
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 检查token是否过期
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取token过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration;
    }


    /**
     * 根据token获取username
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username = getClaimsFromToken(token).getSubject();
        return username;
    }


}
