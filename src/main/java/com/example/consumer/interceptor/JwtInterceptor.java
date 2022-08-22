package com.example.consumer.interceptor;

import com.example.consumer.dao.UserDao;
import com.example.consumer.exception.ConsumerErrorCode;
import com.example.consumer.exception.ConsumerException;
import com.example.consumer.exception.InterceptorErrorCode;
import com.example.consumer.exception.InterceptorException;
import com.example.consumer.model.UserDto;
import com.example.consumer.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@CrossOrigin(origins = "*")
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception, InterceptorException {
        log.info("JwtInterceptor - " + request.getMethod() + " : " + request.getServletPath());

        // TODO 만료 시간 연장 HTTP

        String requestURI = request.getRequestURI();

        // option 요청은 바로 통과
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            // request의 parameter에서 auth_token으로 넘어온 녀석을 찾아본다.

//            String token = request.getCookies()

            log.info(request.getHeaderNames().toString());

            String token = request.getHeader("Access-Token");
            log.info(request.getHeader("Access-Token"));
            if (token != null && token.length() > 0) {
                // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생시킨다.
                log.info("Checking token authenticity");
                if (jwtService.checkValid(token)) {
                    // 여기서 http 필드를 봐서, last 가 5분 이내면 토큰 다시 만들어주던가, 연장만하던그ㅡㅡ
                    Date now = new Date();
                    if ((long)jwtService.get(token).get("exp") - now.getTime()/1000 < 300) { // 5분 이내
                        String newToken = jwtService.create(
                                UserDto.builder()
                                        .email(jwtService.getEmail(token))
                                        .build()
                        );
                        throw new InterceptorException(InterceptorErrorCode.TOKEN_REFRESH, newToken);
                    }

                    return true;
                }
                else {
                    log.info("Token not valid");
                    // token expired
                    throw new InterceptorException(InterceptorErrorCode.TOKEN_EXPIRED);
                }
            }
            else {
                log.info("Token does not exist");
                throw new InterceptorException(InterceptorErrorCode.TOKEN_EMPTY);
            }


        }
    }
}
