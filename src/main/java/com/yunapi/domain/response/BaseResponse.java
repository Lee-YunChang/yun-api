package com.yunapi.domain.response;

import com.yunapi.util.MessageUtils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseResponse {
	protected String result;
	protected String reason;
	
	public BaseResponse(){
		this.result = MessageUtils.SUCCESS;
		this.reason  = "";
	}
	public BaseResponse(String result, String reason){
		this.reason = reason;
		this.result = result;
	}
}
