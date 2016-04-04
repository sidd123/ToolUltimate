package com.tcs.toolultimate.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@ComponentScan(basePackages="com.tcs.toolultimate")
@EnableWebMvc
@PropertySource("classpath:mongodb.properties")
public class MvcConfiguration extends WebMvcConfigurerAdapter{
	
	@Value("${mongodb.url}")
	private String mongodbUrl;
	
	
	@Value("${mongodb.port}")
	private String mongodbPort;
	
	@Value("${mongodb.database}")
	private String mongodbDB;
	
	@Value("${mongodb.username}")
	private String mongoUser;
	

	@Value("${mongodb.password}")
	private String mongoPassword;

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		ServerAddress address = new ServerAddress(mongodbUrl, Integer.parseInt(mongodbPort));
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(MongoCredential.createCredential(mongoUser, mongodbDB, mongoPassword.toCharArray()));
		MongoClient client = new MongoClient(address,credentials);
		
		MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(client, mongodbDB));
		
		return mongoTemplate;
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	
}
