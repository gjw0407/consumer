package com.example.consumer.interceptor;

import com.example.consumer.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("JwtInterceptor - " + request.getMethod() + " : " + request.getServletPath());

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
                jwtService.checkValid(token);
//                response.sendRedirect("/web/login?redirectURL=" + requestURI);
                return true;
            } else {
                log.info("Token not valid");
                return false;
            }
        }
    }
}
