package com.yunapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@EnableAsync
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer, Filter {
	
	private static final String[] RESOURCE_LOCATIONS = {
	        "classpath:/static/"
	        };
	
	@Value("${bankq-api.image-upload-path}")
    private String imageUploadPath;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html")
        	.addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        
    	registry.addResourceHandler("/static/resources/**")
	    	.addResourceLocations("file:///" + imageUploadPath +"/")
	    	.setCachePeriod(3600)
	    	.resourceChain(true)
	    	.addResolver(new PathResourceResolver());
    	
    	List<String> imageFolders = Arrays.asList("logo", "magazine", "resource", "recruit", "splash", "businessType", "smarteditor2", "app");
        for(String imageFolder : imageFolders) {
        	registry.addResourceHandler("/images/" +imageFolder +"/**")
        	.addResourceLocations("file:///" + imageUploadPath + "/" + imageFolder +"/")
        	.setCachePeriod(3600)
        	.resourceChain(true)
        	.addResolver(new PathResourceResolver());
        }
    	
        registry.addResourceHandler("/static/**")
        		.addResourceLocations(RESOURCE_LOCATIONS)
        		.setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setViewNames("view/*");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {		
		 HttpServletResponse response = (HttpServletResponse) res;
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, PATCH, OPTIONS");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept, Authorization, s-Id, m-Id, s-token, m-token");
	        chain.doFilter(req, res);
	       
	}
    
    @Bean
    public MappingJackson2JsonView jsonView() {
    	return new MappingJackson2JsonView();
    }
    
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    	ThreadPoolTaskExecutor taskExecutor = threadPoolTaskExecutor();
	    taskExecutor.setCorePoolSize(10);
	    taskExecutor.setMaxPoolSize(20);
	    taskExecutor.setQueueCapacity(50);
	    configurer.setTaskExecutor(taskExecutor);
    }
    
    @Bean(name = "threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
	       return new ThreadPoolTaskExecutor();
	}
}
