package com.yunapi.controller;

import com.yunapi.domain.response.BaseResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(value = "/token")
@ConfigurationProperties(prefix = "yun-api.token")
@RequiredArgsConstructor
@RestController
public class TokenController {
	
	@Setter private String typ;
	@Setter private String alg;
	@Setter private String apikey;
	@Setter private String secretkey;
	
	@PostMapping(value = "")
	public ResponseEntity<? extends BaseResponse> createToken(@RequestBody TokenRequest tokenRequest) throws InvalidKeyException, UnsupportedEncodingException {
		log.info("nonce::::" + String.valueOf(System.currentTimeMillis()));
		if(StringUtils.isBlank(tokenRequest.getApikey()) || StringUtils.isBlank(tokenRequest.getNonce())) {
			log.info("apikey 또는 nonce가 없습니다.");
			return ResponseEntity.badRequest().body(new BaseResponse("400", "apikey 또는 nonce가 없습니다."));
		}if(!tokenRequest.getApikey().equals(apikey)) {
			log.info("apikey가 일치하지 않습니다.");
			return ResponseEntity.badRequest().body(new BaseResponse("400", "apikey가 일치하지 않습니다."));
		}
		
		try {
			Date nonce = new Date(Long.valueOf(tokenRequest.getNonce()));
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MINUTE, -30);
			Calendar c2 = Calendar.getInstance();
			c2.add(Calendar.MINUTE, 30);
			if(nonce.getTime() < c.getTime().getTime() || c2.getTime().getTime() < nonce.getTime()) {
				log.info("nonce값이 유효하지 않습니다.(만료 또는 현재시각보다 클 경우)");
				return ResponseEntity.badRequest().body(new BaseResponse("400", "nonce값이 유효하지 않습니다.(만료 또는 현재시각보다 클 경우)"));
			}
		} catch(Exception e) {
			log.info("nonce값은 millisecond값으로 설정해야 합니다.");
			return ResponseEntity.badRequest().body(new BaseResponse("400", "nonce값은 millisecond값으로 설정해야 합니다."));
		}
		
		SecretKey key = Keys.hmacShaKeyFor(secretkey.getBytes("UTF-8"));
		Map<String, Object> header = new HashMap<>(), payloads = new HashMap<>();
		header.put("typ", typ);
		header.put("alg", alg);
		
		payloads.put("apikey", apikey);
		payloads.put("exp", Long.valueOf(tokenRequest.getNonce()) + 1800000L);
		
        String jwt = Jwts.builder()
        		.setHeader(header)
        		.setClaims(payloads)
                .signWith(key)  
                .compact();
        
        log.info(">>>> 생성된 token : " + jwt);
        return ResponseEntity.ok().body(new TokenResponse(jwt));
    }

	@Getter @Setter
	public static class TokenRequest {
		private String apikey;
		private String nonce;
	}
	
	@Getter
	public class TokenResponse extends BaseResponse {
		private String token;
		
		public TokenResponse(String token) {
			this.token = token;
		}
	}
}
