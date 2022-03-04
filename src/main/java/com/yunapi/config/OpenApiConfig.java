package com.yunapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OpenApiConfig {

	@Bean
	public OpenAPI openAPI(@Value("${yun-api.version}") String appVersion,
			@Value("${yun-api.url}") String url, @Value("${spring.profiles.active}") String active) {
		Info info = new Info().title("befin API v2 - " + active).version(appVersion)
				.description("yun API 입니다.")
				.termsOfService("http://swagger.io/terms/")
				.contact(new Contact().name("yun").url("https://www.yun.co.kr").email("dbsckd512@naver.co.kr"))
				.license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));
		
		List<Server> servers = Arrays.asList(new Server().url(url).description("demo (" + active +")"));
				
		return new OpenAPI()
				.info(info)
				.servers(servers);
	}
	
}