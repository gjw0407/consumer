package com.example.consumer.service.jwt;

import java.security.Key;
import java.util.Map;
import java.util.Random;
import java.nio.charset.StandardCharsets;

import org.springframework.data.util.Pair;

import io.jsonwebtoken.security.Keys;

public class JwtKey {
	
	// NEVER EXPOSE!!
	private static final Map <String, String> SECRET_KEY_SET = Map.of(
			"key1" , "SCHDULEKEYFORSECURITYSCHDULEKEYFORSECURITYSCHDULEKEYFORSECURITY",
			"key2" , "CHDULEKEYFORSECURITYSCHDULEKEYFORSECURITYSCHDULEKEYFORSECURITYS",
			"key3" , "HDULEKEYFORSECURITYSCHDULEKEYFORSECURITYSCHDULEKEYFORSECURITYSC"
			);
	private static final String[] KID_SET = SECRET_KEY_SET.keySet().toArray(new String[0]);
	private static Random randomIndex = new Random();
	
	public static Key getKey(String kid) { 
		 String key = SECRET_KEY_SET.getOrDefault(kid, null); 
		 if (key == null) 
			 return null; 
		 return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	}
		 /** 
		  * SECRET_KEY_SET 에서 랜덤한 KEY 가져오기
		 * 
		  * @return kid와 key Pair 
		  */ 
	 public static Pair<String, Key> getRandomKey() { 
		 String kid = KID_SET[randomIndex.nextInt(KID_SET.length)];
		 String secretKey = SECRET_KEY_SET.get(kid);
		 return Pair.of(kid, Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))); 
	 }
}
