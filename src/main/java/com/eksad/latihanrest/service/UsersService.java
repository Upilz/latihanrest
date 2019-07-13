package com.eksad.latihanrest.service;

import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eksad.latihanrest.dao.UsersRepositoryDao;
import com.eksad.latihanrest.model.Users;

@Service
public class UsersService implements UserDetailsService {

	//Autowired
	UsersRepositoryDao usersRepositoryDao;
	
	//Check username exist or not
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Users user = usersRepositoryDao.findByUsername(username);
		if(user != null)
		{
			GrantedAuthority autorithy = new SimpleGrantedAuthority(user.getRole());
			return new User (user.getUsername(), user.getPassword(), Arrays.asList(autorithy));
		}
		else
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		
	}

	
	

}
