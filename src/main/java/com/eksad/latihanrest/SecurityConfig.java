package com.eksad.latihanrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eksad.latihanrest.service.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//autowired usersService
	@Autowired
	private UsersService usersService;
	
	//allow access userscontroller from enablewebsecutity(copy)
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic() //read basic auth
 		.and().csrf().disable() //can access other method together
 		.authorizeRequests() //url access
 		.antMatchers("/admin/**").hasAuthority("ADMIN") //access as admin (only admin)
 		.antMatchers("/user/**").hasAnyAuthority("ADMIN","USER") //access as user (user as user, admin can access as user)
 		.and().formLogin().permitAll(); //session log in sendiri
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 		//set each pw first
// 		//admin
// 		String admin ="admin";
// 		String adminPassword = encoder().encode("curut");
// 		//user
// 		String user ="user";
// 		String userPassword = encoder().encode("lulung");
// 		
// 		//Memory Authentication
// 		auth.inMemoryAuthentication()
// 		.withUser(admin).password(adminPassword).roles("ADMIN","USER");
// 		//Memory Authentication
// 		auth.inMemoryAuthentication()
// 		.withUser(user).password(userPassword).roles("USER");
 		
 		//call users service
 		auth.userDetailsService(usersService).passwordEncoder(encoder());
 	
 	}
 	
 	//BEAN for each entity pw /raw pw
 	@Bean
 	public BCryptPasswordEncoder encoder()
 	{
 		System.out.println(new BCryptPasswordEncoder().encode("curut"));
 		return new BCryptPasswordEncoder();
 	}

}
