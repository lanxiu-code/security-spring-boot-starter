package com.lx.security.interceptor;

import com.lx.security.config.SecurityConfig;
import com.lx.security.constant.SecurityConstant;
import com.lx.security.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ServerProtectInterceptor implements HandlerInterceptor {
    @Resource
    private SecurityConfig securityConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!securityConfig.getOnlyGateway()){
            return true;
        }
        String auth = request.getHeader(securityConfig.getAuthKey());
        if(securityConfig.getAuthValue().equals(auth)){
            return true;
        }else{
            //String result = "{\"code\":403,\"data\":null,\"message\":\"非法请求\"}";
            WebUtils.render(response, HttpStatus.FORBIDDEN.value());
            return false;
        }
    }

}
