package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.entities.Order;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.exception.BadRequestException;
import com.jup.bookorder.bookorder.repositories.UsersRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private OrderService orderService;

    @Value("${user.credentail.salt}")
    private String salt;

    @Transactional
    public User save(User user){
        if(usersRepo.findByUsername(user.getUsername()) != null){
            throw new BadRequestException("username is already exist");
        }

        user.setPassword(hashPassword(user.getPassword()));
		return usersRepo.save(user);
	}

    public String hashPassword(String password){
        return DigestUtils.sha256Hex(password+salt);
    }

    @Transactional
    public void delete(User user){
        if(usersRepo.findOne(user.getId()) == null ){
            throw new RuntimeException("user has been already deleted");
        }

        usersRepo.delete(user.getId());
        orderService.deleteOrderByUser(user);

    }
}
