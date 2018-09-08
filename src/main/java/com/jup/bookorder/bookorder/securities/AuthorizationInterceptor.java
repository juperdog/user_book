package com.jup.bookorder.bookorder.securities;

import com.jup.bookorder.bookorder.services.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final String AUTHORIZATION_NAME = "Bearer";

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserCredential userCredential;

    Set<String> interestingPath = new HashSet<>(Arrays.asList(
        "GET/users",
        "DELETE/users",
        "POST/users/orders"
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //this interceptor will intercept when it has been declared in interestingPath
        if(!interestingPath.contains(request.getMethod()+request.getRequestURI())){
            return true;
        }

        //Check "Authorization" in request header
        String authorization = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if (authorization == null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        //Seperate Authorization header and it has to start with "Bearer"
        String[] authorizations = authorization.split(" ", 2);
        if (!AUTHORIZATION_NAME.equalsIgnoreCase(authorizations[0]) || authorizations.length != 2) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        //Check accessToken
        String accessToken = authorizations[1];
        if(!loginService.isValidToken(accessToken)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        //copy value to request scope bean
        UserCredential userCredentialByAccessToken = loginService.getUserCredentialByAccessToken(accessToken);
        if(userCredentialByAccessToken != null ){
            userCredential.setUsername(userCredentialByAccessToken.getUsername());
            userCredential.setPassword(userCredentialByAccessToken.getPassword());
            userCredential.setExpire(userCredentialByAccessToken.getExpire());
            userCredential.setAccessToken(userCredentialByAccessToken.getAccessToken());
            userCredential.setUser(userCredentialByAccessToken.getUser());
        }
        return true;
    }

}
