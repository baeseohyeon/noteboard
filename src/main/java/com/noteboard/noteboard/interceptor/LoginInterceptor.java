package com.noteboard.noteboard.interceptor;

import com.noteboard.noteboard.util.Sessions;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        HttpSession httpSession = request.getSession();
        String sessionItem = (String) httpSession.getAttribute(Sessions.SESSION_ID);

        if (sessionItem == null) {
            response.getOutputStream().println("LOGIN REQUIRED!");
            return false;
        }
        return true;
    }
}
