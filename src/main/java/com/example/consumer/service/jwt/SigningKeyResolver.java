package com.example.consumer.service.jwt;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;

/*
 * JwsHeader를 통해 kid로 Key 찾기
 * 
 */
public class SigningKeyResolver extends SigningKeyResolverAdapter{
	public static SigningKeyResolver instance = new SigningKeyResolver(); 
	
	@Override 
	public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) { 
	String kid = jwsHeader.getKeyId(); 
	if (kid == null)
		return null; 
	return JwtKey.getKey(kid);
	}
}
