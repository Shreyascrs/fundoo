package com.bridgeit.fundoonotes.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class Appconfigure {
	@Bean
public ModelMapper getModelMapper()
{

	ModelMapper mapper=new ModelMapper();
//	mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	return mapper;
	
}
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
