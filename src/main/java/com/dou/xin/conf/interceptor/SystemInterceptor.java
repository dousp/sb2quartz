package com.dou.xin.conf.interceptor;

import com.dou.xin.core.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SystemInterceptor implements HandlerInterceptor {

    static Logger logger = LoggerFactory.getLogger(SystemInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = IpUtil.getIpAddr(request);
        logger.info("请求路径：[{}] - [{}] - [{}]", ip, request.getRemotePort(), request.getRequestURI());
        return true;
    }
}
