package com.example.consumer.service.user;

import com.example.consumer.model.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<Map<String, Object>> login(UserDto user);
    ResponseEntity<Map<String, Object>> register(UserDto user);
}
