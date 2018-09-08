package com.jup.bookorder.bookorder.dto.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wasan_kha on 9
 * /3/2018 AD.
 */
@Getter
@Setter
public class UserLoginRequest {
    @NotBlank(message="User should not be blank or null")
    private String username;
    @NotBlank(message="Password should not be blank or null")
    private String password;

}
