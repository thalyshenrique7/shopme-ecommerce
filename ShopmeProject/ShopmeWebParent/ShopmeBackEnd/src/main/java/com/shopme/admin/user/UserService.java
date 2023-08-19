package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private RoleRepository roleRepo;

	public List<User> findAll() {
		return (List<User>) repo.findAll();
	}

	public List<Role> findRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	public void save(User user) {
		repo.save(user);
	}

}
