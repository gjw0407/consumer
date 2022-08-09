package com.example.consumer.service.user;

import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.User;
import com.example.consumer.exception.ConsumerErrorCode;
import com.example.consumer.exception.ConsumerException;
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

        validateLoginIdAndPwd(userDto);

        // jwt.io에서 확인
        // 로그인 성공했다면 토큰을 생성한다
        String token = jwtService.create(userDto); // TODO: 토큰예외 따로 처리
        log.info("로그인 토큰정보 : {}", token); // TODO: 토큰에서 로깅
        // 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("auth-token", token);
        resultMap.put("user-email", userDto.getEmail());
        log.info("로그인 성공");
        HttpStatus status = HttpStatus.ACCEPTED;

        return new ResponseEntity<>(resultMap, status);
    }

    private void validateLoginIdAndPwd(UserDto userDto) {
        // ErrorMessage 다르게 할 수도 있음
        User user = userDao.findByEmail(userDto.getEmail())
                .orElseThrow(()-> new ConsumerException(ConsumerErrorCode.LOGIN_ERROR));
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new ConsumerException(ConsumerErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> register(UserDto userDto) {

        Map<String, Object> resultMap = new HashMap<>();

        userDao.findByEmail(userDto.getEmail())
                .ifPresent((user)-> {
                    throw new ConsumerException(ConsumerErrorCode.REGISTER_DUPLICATE_EMAIL);
                });

        // Dto to entity
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePassword);
        User userET = userDto.toEntity();
        userDao.save(userET);
        resultMap.put("message", "가입 성공");
        HttpStatus status = HttpStatus.ACCEPTED;

        return new ResponseEntity<>(resultMap, status);
    }

    @Override
    public int getUserId(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(()-> new ConsumerException(ConsumerErrorCode.INTERNAL_SERVER_ERROR))
                .getUserId();
    }
}
