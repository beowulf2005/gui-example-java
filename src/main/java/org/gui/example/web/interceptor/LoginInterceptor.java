package org.gui.example.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.gui.example.entity.user.TUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        LOG.debug(path);

        if (path.startsWith("/login")) {
            return true;
        }
        final Object user = request.getSession().getAttribute("user");
        if (user != null && user instanceof TUser && ((TUser) user).getId() != null) {
            return true;
        }
        return false;
    }
}
