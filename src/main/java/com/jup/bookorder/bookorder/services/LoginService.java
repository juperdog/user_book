package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.dto.UserCredential;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.exception.BadCredentialException;
import com.jup.bookorder.bookorder.repositories.UsersRepo;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wasan_kha on 9/3/2018 AD.
 */
@Service
public class LoginService {

    @Autowired
    private UsersRepo usersRepo;

    private Map<String, User> accessTokenMapping = new HashMap<>();

    public String login(UserCredential userCredential){
        User user = usersRepo.findByUsername(userCredential.getUsername());

        if(user != null && user.getPassword().equals(userCredential.getPassword())){
            String accessToken = RandomStringUtils.randomAlphanumeric(64);
            accessTokenMapping.put(accessToken, user);
            return accessToken;
        }

        throw new BadCredentialException();
    }

}
