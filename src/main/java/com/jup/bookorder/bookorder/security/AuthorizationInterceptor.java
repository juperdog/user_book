package com.jup.bookorder.bookorder.security;

import com.jup.bookorder.bookorder.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final String authorizationHeaderName = "Authorization";

    @Autowired
    private LoginService loginService;

    Map<String, String> ignorePath = new HashMap<String, String>()
    {{
        put("/login", "POST");
        put("/users", "POST");
        put("/books", "GET");
    }};



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //ignore security
        String ignoreMethodFromUri = ignorePath.get(request.getRequestURI());
        if(ignoreMethodFromUri != null && ignoreMethodFromUri.equals(request.getMethod())){
            return true;
        }

        String authorization = request.getHeader(authorizationHeaderName);
        if (authorization == null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        String[] authorizations = authorization.split(" ", 2);
        if (!"Bearer".equalsIgnoreCase(authorizations[0]) || authorizations.length != 2) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        String accessToken = authorizations[1];
        if(!loginService.isValidToken(accessToken)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        request.setAttribute("security_accessToken", accessToken);
        request.setAttribute("security_user", loginService.getUserByAccessToken(accessToken));
        return true;
    }

}
