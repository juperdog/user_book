package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.dto.UserRequest;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.repositories.UsersRepo;
import com.jup.bookorder.bookorder.security.UserCredential;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wasan_kha on 9/3/2018 AD.
 */
@Service
public class LoginService {

    @Autowired
    private UsersRepo usersRepo;

    private Map<String, UserCredential> accessTokenMapping = new HashMap<>();
    private List<String> needToBeRemove = new ArrayList<>();

    public String login(UserRequest userRequest){
        User user = usersRepo.findByUsername(userRequest.getUsername());

        if(user != null && user.getPassword().equals(userRequest.getPassword())){
            String accessToken = RandomStringUtils.randomAlphanumeric(64);
            UserCredential userCredential = new UserCredential(user.getUsername(), user.getPassword(), user);
            accessTokenMapping.put(accessToken, userCredential);
            return accessToken;
        }

        throw new RuntimeException("Authentication : Bad password");
    }

    public boolean isValidToken(String accessToken){
        return accessTokenMapping.get(accessToken) != null;
    }

    public User getUserByAccessToken(String accesToken) {

        if(accessTokenMapping.get(accesToken) != null)
            return accessTokenMapping.get(accesToken).getUser();

        return null;
    }


    public void cleanupAccessToken(){
        needToBeRemove.clear();

        for(Map.Entry<String, UserCredential> item : accessTokenMapping.entrySet()){
            UserCredential userCredential = item.getValue();
            if(userCredential.getExpire() < System.currentTimeMillis())
                needToBeRemove.add(item.getKey());
        }

        for(String item : needToBeRemove){
            accessTokenMapping.remove(item);
        }
    }
}
