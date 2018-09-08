package com.jup.bookorder.bookorder.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Getter
@Setter
public class UserRequest {

    @NotBlank(message="User should not be blank or null")
    private String username;

    @NotBlank(message="Password should not be blank or null")
    private String password;

    @JsonProperty("date_of_birth")
    @NotBlank(message="date_of_birth should not be blank or null")
    private String dateOfBirth;

}
