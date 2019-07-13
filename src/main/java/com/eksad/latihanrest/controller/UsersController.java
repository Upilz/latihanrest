package com.eksad.latihanrest.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.UsersRepositoryDao;
import com.eksad.latihanrest.model.Users;

@RestController
@RequestMapping("admin")
public class UsersController {
	
	//autowired
	@Autowired
	UsersRepositoryDao usersRepositoryDao;
	
	//requestmap
	//AS ADMIN + Hashmap
	@RequestMapping("")
	public HashMap<String, Object> admin()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Log In As an ADMIN");
		return map;
	}
	
	//AS USER + Hashmap
	@RequestMapping("user")
	public HashMap<String, Object> user()
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Log In As a USER");
		return map;
	}
		
	//method get,post,delete
	
	//GET ALL
	@GetMapping("getAll")
	public List <Users> getAll()
	{
		return usersRepositoryDao.findAll();
	}
	
	//SAVE - POST
	@PostMapping("save")
	public Users save(@RequestBody Users user)
	{
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); //buat set pw tanpa enkrip pw
		return usersRepositoryDao.save(user);
	}
	
	
	//DELETE
	@DeleteMapping("delete/{id}")
	public String delete (@PathVariable int id)
	{
		usersRepositoryDao.deleteById(id);
		return "Delete Data Success";
	}
	

}
