package com.blog.blog.common.authorizations.tokenManager;

import com.blog.blog.repository.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager {
    public String encrypt(UserDto userDto, String salt){
        Map<String,Object> user = new HashMap<>();
        user.put("userNo", userDto.getUserNo());
        user.put("userId", userDto.getUserId());
        user.put("userNickname", userDto.getUserNickname());
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("Server")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ Duration.ofHours(24).toHours()))
                .setClaims(user)
                .signWith(SignatureAlgorithm.HS512, salt.getBytes())
                .compact();
    }
    public Claims decrypt(String token, String salt){
        return Jwts.parser()
                .setSigningKey(salt.getBytes())
                .parseClaimsJws(token)
                .getBody();

    }
}
