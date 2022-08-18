package com.example.consumer.service.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.example.consumer.model.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
	
    private Long expireMin = 1L; // 분

    // 로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성하여 반환.
    @Override
    public String create(UserDto userDto) {
        JwtBuilder jwtBuilder = Jwts.builder();
        Pair<String, Key> key = JwtKey.getRandomKey();

        // Header 설정 : alg(알고리즘종류), kid
        jwtBuilder.setHeaderParam("typ", "JWT")
        		.setHeaderParam(JwsHeader.KEY_ID, key.getFirst()); 
        
        // Payload 설정
        Date now = new Date();
        jwtBuilder.setSubject(userDto.getEmail()) // claim에도, subject에도 있는 email 이유??
        		.setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 1000 * 60 * expireMin)) // 유효기간 설정
                .claim("email", userDto.getEmail()); // 클레임 : user email

        // signature 설정
        jwtBuilder.signWith(key.getSecond()); // 어디쓰이는 지

        // 마지막 직렬화 처리
        String jwt = jwtBuilder.compact();
        return jwt;
    }

    // 전달 받은 토큰이 제대로 생성된것이니 확인 하고 문제가 있다면 RuntimeException을 발생.
    @Override
    public boolean checkValid(String jwt) {
        try{
        Jwts.parserBuilder()
			.setSigningKeyResolver(SigningKeyResolver.instance)
			.build().parseClaimsJws(jwt);
        return true;
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e)
        {   log.info("잘못된 JWT 서명입니다.");
            return false;
        } catch(ExpiredJwtException e)
        {   log.info("만료된 JWT 토큰입니다.");
            return false;
        } catch(UnsupportedJwtException e)
        {   log.info("지원되지 않는 JWT 토큰입니다.");
            return false;
        } catch(IllegalArgumentException e)
        {   log.info("JWT 토큰이 잘못되었습니다.");
            return false;
        } catch (NullPointerException ex){
            log.error("JWT Refresh Token 없습니다.");
            return false;
        }
}

//    @Override
//    public boolean checkExpiration(String jwt){
//        Jwts.parserBuilder()
//                .setSigningKeyResolver(SigningKeyResolver.instance)
//                .build().parseClaimsJws(jwt)
//                .g
//    }


    // JWT Token을 분석해서 필요한 정보를 반환.
    @Override
    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
        	claims = Jwts.parserBuilder()
	    			.setSigningKeyResolver(SigningKeyResolver.instance)
	    			.build().parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        // Claims는 Map의 구현체이다.
        return claims.getBody();
    }
    
    @Override
    public String getEmail(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKeyResolver(SigningKeyResolver.instance)
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
    }
}
