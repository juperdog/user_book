package com.jup.bookorder.bookorder.securities;

import com.jup.bookorder.bookorder.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Configuration
public class SecurityScheduler {

    @Autowired
    private LoginService loginService;

    @Scheduled(fixedDelay = 300000)
    public void cleanupAccessToken() {
        System.out.println("cleanupAccessToken");
        loginService.cleanupAccessToken();
    }
}
