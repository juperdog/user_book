package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.dto.requests.UserLoginRequest;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.exception.BadRequestException;
import com.jup.bookorder.bookorder.repositories.UsersRepo;
import com.jup.bookorder.bookorder.securities.UserCredential;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UsersService usersService;

    @Value("${user.accesstoken.timeout}")
    private long timeout;

    private Map<String, UserCredential> accessTokenMapping = new HashMap<>();
    private List<String> needToBeRemove = new ArrayList<>();

    public String login(UserLoginRequest userRequest){
        User user = usersRepo.findByUsername(userRequest.getUsername());

        String hashedPassword = usersService.hashPassword(userRequest.getPassword());
        if(user != null && user.getPassword().equals(hashedPassword)){
            String accessToken = RandomStringUtils.randomAlphanumeric(64);
            UserCredential userCredential = new UserCredential(user.getUsername(), user.getPassword(), user, System.currentTimeMillis()+timeout, accessToken);
            accessTokenMapping.put(accessToken, userCredential);
            return accessToken;
        }

        throw new BadRequestException("Authentication : Bad user or password");
    }

    public boolean isValidToken(String accessToken){
        return accessTokenMapping.get(accessToken) != null;
    }

    public UserCredential getUserCredentialByAccessToken(String accesToken) {

        UserCredential userCredential = accessTokenMapping.get(accesToken);
        if(userCredential != null) {
            if(!isExpired(userCredential)){
                return userCredential;
            }
        }

        return null;
    }


    public void cleanupAccessToken(){
        //find the accessToken that is expired and store to the list
        for(Map.Entry<String, UserCredential> item : accessTokenMapping.entrySet()){
            UserCredential userCredential = item.getValue();
            if(isExpired(userCredential))
                needToBeRemove.add(item.getKey());
        }

        //remove expired data
        for(String item : needToBeRemove){
            accessTokenMapping.remove(item);
        }

        //cleanup the list
        needToBeRemove.clear();
    }

    protected boolean isExpired(UserCredential userCredential){
        return userCredential.getExpire() < System.currentTimeMillis();
    }

    public void revokeAccessToken(String accessToken){
        accessTokenMapping.remove(accessToken);
    }
}
