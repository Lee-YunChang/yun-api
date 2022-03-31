package com.yunapi.domain.response;

import com.yunapi.domain.dto.UserDto;
import com.yunapi.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends BaseResponse {

    private String IsJoin = "N";
    private UserDto user;

    public void setUser(User user) {
        this.user = new UserDto(user);
    }
    
}
