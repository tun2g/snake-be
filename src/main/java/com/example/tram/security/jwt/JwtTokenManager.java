package com.example.tram.security.jwt;


import com.example.tram.config.redis.IRedisCacheService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenManager {

    @Value("${spring.jwt.secret_key}")
    private String SECRET;

    @Value("${spring.redis.key_auth}")
    private String KEY_AUTH;

    @Autowired
    private IRedisCacheService redisCacheService;

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaim(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String username,Integer expiredTime){

        Map<String,String> claims = new HashMap<>();

        String token =  Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

        String keyRedis = KEY_AUTH + ":" + token;
        redisCacheService.set(keyRedis,username, expiredTime);

        return token;
    }
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isExpiredToken(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean verifyToken(String token, UserDetails user){
        String keyRedis = KEY_AUTH + ":" + token;
        String ownerToken =  redisCacheService.get(keyRedis).toString();

        final String username = extractSubject(token);

        if(!ownerToken.equals(username)){
            return false;
        }
        return (username.equals(user.getUsername()) && !isExpiredToken(token));

    }
}
