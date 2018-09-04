package com.jup.bookorder.bookorder.security;

import com.jup.bookorder.bookorder.entities.User;
import lombok.Data;

import java.util.Date;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Data
public class UserCredential {
    private String username;
    private String password;
    private long expire;
    private User user;

    public UserCredential(String username, String password, User user){
        this.username = username;
        this.password = password;
        this.user = user;

        this.expire = System.currentTimeMillis()+600000;
    }
}
