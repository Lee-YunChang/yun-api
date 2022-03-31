package com.yunapi.util;

import com.yunapi.domain.request.CommonRequest;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AuthCode {


	public static int RandomCode(){
		Random random = new Random();
        
		int result = random.nextInt(10000)+1000;
		 
		if(result>10000){
		    result = result - 1000;
		}
			return result;	
	}

	public static String SecurityCode(){		
		Random random = new Random();
		int result = random.nextInt(100000)+10000;
		 
		if(result>100000){
		    result = result - 10000;
		}
		
		return String.valueOf(result);
	}
	public static boolean validateId(CommonRequest value) throws RuntimeException{
		if( value.getId().equals(value.getSId()))
			return true;
		else
			throw new RuntimeException("forbidden access");
	}
}
