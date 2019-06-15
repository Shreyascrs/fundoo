package com.bridgeit.fundoonotes.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.exception.UserException;
import com.bridgeit.fundoonotes.exception.UserExceptionHandler;

@Configuration
public class Appconfigure {
	@Bean
	public ModelMapper getModelMapper() {

		ModelMapper mapper = new ModelMapper();
		return mapper;

	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(destroyMethod = "close")
	public RestHighLevelClient client() {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		return client;
	}

	@Bean
	public Response getResponse() {
		return new Response();
	}

@Bean
	public UserExceptionHandler exceptionHandler() {
		return new UserExceptionHandler();
	}

//	@Bean
//	public UserException userException(String message) {
//		return new UserException(message);
//	}
}
