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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/web")
@CrossOrigin(origins = {"*"})
public class UserController {

    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> webRegister(@RequestParam String email, @RequestParam String password) {
        log.info("Regiseter - 호출");
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();

        UserDto user = new UserDto();
        user.setEmail(email);
        user.setPassword(password);

        log.info("{}, {}", user.getEmail(), user.getPassword());

        try {
            boolean loginUser = userService.register(user);
            if (loginUser) {
                resultMap.put("message", "가입 성공");
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", "가입 실패");
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> webLogin(@RequestParam String email, @RequestParam String password) {
        log.info("Login - 호출");

        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();

        UserDto user = new UserDto();
        user.setEmail(email);
        user.setPassword(password);

        try {
            boolean loginUser = userService.login(user);
            loginUser = true;
            if (loginUser) {
                // jwt.io에서 확인
                // 로그인 성공했다면 토큰을 생성한다
                String token = jwtService.create(user);
                log.trace("로그인 토큰정보 : {}", token);

                // 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다
                resultMap.put("auth-token", token);
                resultMap.put("admin-email", email);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", "로그인 실패");
                status = HttpStatus.UNAUTHORIZED;
            }
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
