package com.swu.lease.web.admin.custom.interceptor;

import com.swu.lease.common.ExceptionHandle.LeaseException;
import com.swu.lease.common.login.LoginUser;
import com.swu.lease.common.login.LoginUserHolder;
import com.swu.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtils;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        Claims claims = JwtUtils.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        LoginUserHolder.setLoginUser(new LoginUser(userId, username));
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}