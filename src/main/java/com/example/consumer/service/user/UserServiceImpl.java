package com.example.consumer.service.user;

import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.User;
import com.example.consumer.model.UserDto;
import com.example.consumer.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserDao userDao;

    @Override
    public ResponseEntity<Map<String, Object>> login(UserDto userDto) {

        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();

        // check DB for user information
        User userET = userDao.findByEmail(userDto.getEmail());
        String encodedDbPassword = userDao.findPwd(userDto.getEmail());

        // TODO try catch vs if
        try {
            // check email
            if (!userET.getEmail().equals(userDto.getEmail())) {
                log.error("로그인 실패 : 아이디가 존재하지 않습니다.");
                resultMap.put("message", "아이디가 존재하지 않습니다");
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<>(resultMap, status);
            }

            // check password
            if (!passwordEncoder.matches(userDto.getPassword(), encodedDbPassword)) {
                log.error("로그인 실패 : 비밀번호가 일치하지 않습니다.");
                resultMap.put("message", "비밀번호가 일치하지 않습니다");
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<>(resultMap, status);
            }

            // check
            userDto.setPassword(encodedDbPassword);
            if (!userDao.existsByEmailAndPassword(userDto.getEmail(), userDto.getPassword())) {
                log.error("로그인 실패 : 아이디와 비밀번호가 일치하지 않습니다.");
                resultMap.put("message", "아이디와 비밀번호가 일치하지 않습니다");
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<>(resultMap, status);
            }

            // jwt.io에서 확인
            // 로그인 성공했다면 토큰을 생성한다
            String token = jwtService.create(userDto);
            log.info("로그인 토큰정보 : {}", token);
            // 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다
            resultMap.put("auth-token", token);
            resultMap.put("user-email", userDto.getEmail());
            log.info("로그인 성공");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @Override
    public ResponseEntity<Map<String, Object>> register(UserDto userDto) {

        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();

        try {
            User user = userDao.findByEmail(userDto.getEmail());
            log.info("{}", user);
            // 회원이 존재
            if (user != null) {
                log.error("가입 실패 : 회원이 이미 존재합니다.");
                resultMap.put("message", "회원이 이미 존재합니다.");
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<>(resultMap, status);
            }

            // Dto to entity
            String encodePassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodePassword);
            User userET = userDto.toEntity();
            userDao.save(userET);
            resultMap.put("message", "가입 성공");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("가입 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @Override
    public int getUserId(String email) {
        User user = userDao.findByEmail(email);
        return user.getUserId();
    }
}
