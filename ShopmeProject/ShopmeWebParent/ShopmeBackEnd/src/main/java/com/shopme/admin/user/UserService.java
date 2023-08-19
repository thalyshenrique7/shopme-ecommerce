package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.admin.role.RoleRepository;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> findAll() {
		return (List<User>) repo.findAll();
	}

	public List<Role> findRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	public void save(User user) {
		encodePassword(user);
		repo.save(user);
	}

	public void encodePassword(User user) {
		String userPasswordEncode = passwordEncoder.encode(user.getPassword());
		user.setPassword(userPasswordEncode);
	}
	
	public boolean isEmailUnique(String email) {
		User userEmail = repo.getUserByEmail(email);
		
		return userEmail == null;
	}
}
