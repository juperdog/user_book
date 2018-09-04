package com.jup.bookorder.bookorder.security;

import com.jup.bookorder.bookorder.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Configuration
public class SecurityScheduler {

    @Autowired
    LoginService loginService;

    @Scheduled(fixedDelay = 10000)
    public void cleanupAccessToken() {
        System.out.println("cleanupAccessToken");
        loginService.cleanupAccessToken();
    }
}
