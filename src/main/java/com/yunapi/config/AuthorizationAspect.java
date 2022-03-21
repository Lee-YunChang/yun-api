package com.yunapi.config;

import com.yunapi.exception.AuthorizationHeaderNotExistsException;
import com.yunapi.exception.ExpiredTokenException;
import com.yunapi.exception.InvalidApikeyException;
import com.yunapi.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Aspect
@Component
public class AuthorizationAspect {
	
	private String apikey;
	private String secretkey;
	
//	@Before("execution(public * kr.co.bankq.yun-apiapi.controller.admin..*Controller.*(..)) ")
	public void insertAdminLog(JoinPoint joinPoint) throws WeakKeyException, UnsupportedEncodingException, ExpiredTokenException {
		SecretKey key = Keys.hmacShaKeyFor(secretkey.getBytes("UTF-8"));
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();		
		String authorization = request.getHeader("Authorization");
		if(StringUtils.isBlank(authorization)){
			authorization = request.getHeader("token");
		} else {
			authorization = authorization.replaceAll("^Bearer( )*", "");			
		}
		if(StringUtils.isBlank(authorization)){ 
			throw new AuthorizationHeaderNotExistsException();
		}
		
		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(key)
            .build()
            .parseClaimsJws(authorization);
			
		if(jwsClaims.getBody() != null) {
			Claims claims = jwsClaims.getBody();
			if(!claims.containsKey("apikey") || !claims.containsKey("exp")) {
				throw new InvalidTokenException();
			}
			if(!apikey.equals(claims.get("apikey").toString())) {
				throw new InvalidApikeyException();
			}
			long exp = Long.valueOf(jwsClaims.getBody().get("exp").toString());
			if(exp < new Date().getTime()) {
				throw new ExpiredTokenException();
			}
		}
	}
	
	@Value("${yun-api.token.apikey}")
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	@Value("${yun-api.token.secretkey}")
	public void setSecretKey(String secretkey) {
		this.secretkey = secretkey;
	}
}
