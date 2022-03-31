package com.yunapi.domain.response;

import com.yunapi.util.MessageUtils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserJoinResponse extends BaseResponse {
	private Long id;
    private String tokenKey;
    private String ci;
    private String di;
    private String email;
    private String recomm;
    
    public UserJoinResponse() {
		super(MessageUtils.FAIL, "");
	}
}
