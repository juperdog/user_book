package com.jup.bookorder.bookorder.securities;

import com.jup.bookorder.bookorder.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Getter
@Setter
@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UserCredential {
    private String username;
    private String password;
    private long expire;
    private User user;

    public UserCredential(){}

    public UserCredential(String username, String password, User user, long expire){
        this.username = username;
        this.password = password;
        this.user = user;
        this.expire = expire;
    }
}
