package com.liuzhihang.archetype.handler;

import com.liuzhihang.archetype.annotation.CheckToken;
import com.liuzhihang.archetype.util.LocalUserUtils;
import com.liuzhihang.archetype.util.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {
        LocalUserUtils.remove();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception {
        log.debug("postHandle");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求方法是否存在注解
        boolean assignableFrom = handler.getClass().isAssignableFrom(HandlerMethod.class);

        if (!assignableFrom) {
            return true;
        }


        CheckToken checkToken = null;
        if (handler instanceof HandlerMethod) {
            checkToken = ((HandlerMethod) handler).getMethodAnnotation(CheckToken.class);
        }

        // 没有加注解 直接放过
        if (checkToken == null) {
            return true;
        }

        if (checkToken.checkAuthorization()) {
            String authorization = request.getHeader("Authorization");
            log.info("header authorization : {}", authorization);
            if (StringUtils.isBlank(authorization)) {
                log.error("从Header中获取Authorization失败");
                return false;
            }

            // 请求远端校验 token

            UserInfo userInfo = new UserInfo();
            // 校验token UserInfo userInfo = xxxService.checkToken(authorization);

            LocalUserUtils.set(userInfo);
        }

        return true;
    }


}