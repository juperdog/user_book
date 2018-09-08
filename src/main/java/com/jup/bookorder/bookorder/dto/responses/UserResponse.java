package com.jup.bookorder.bookorder.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.jup.bookorder.bookorder.entities.Order;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.utils.Utils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Data
public class UserResponse {

    private String name;

    private String surname;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private List<Long> books;

    public UserResponse(User user, List<Order> orderList){
        String username = user.getUsername();
        int indexOfDot = username.indexOf(".");

        if(indexOfDot != -1){
            this.name = username.substring(0, indexOfDot);
            this.surname = username.substring(indexOfDot, username.length());
        }else{
            this.name = username;
            this.surname = "";
        }

        this.dateOfBirth = Utils.dateToString(user.getDateOfBirth());

        books = new ArrayList<>(orderList.size());
        for(Order order : orderList){
            books.add(order.getBookId());
        }
    }
}
