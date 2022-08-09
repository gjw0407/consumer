package com.example.consumer.controller;

import com.example.consumer.model.UserDto;
import com.example.consumer.service.jwt.JwtService;
import com.example.consumer.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/web")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> webRegister(@RequestParam String email,
                                                           @RequestParam String password,
                                                           HttpServletResponse response) {
        log.info("Register - 호출");
        log.info("{}, {}", email, password);

        return userService.register(
                UserDto.builder()
                .password(password)
                .email(email)
                .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> webLogin(@RequestParam String email,
                                                        @RequestParam String password) {
        log.info("Login - 호출");
        log.info("{}, {}", email, password);

        return userService.login(
                UserDto.builder()
                .password(password)
                .email(email)
                .build()
        );
    }

}
