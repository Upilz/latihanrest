package com.eksad.latihanrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eksad.latihanrest.model.Users;

public interface UsersRepositoryDao extends JpaRepository<Users, Integer>
{
	public Users findByUsername (String username);
}
