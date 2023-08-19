package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager em;

	@Test
	public void testCreateUserWithOneRole() {

		Role roleAdmin = em.find(Role.class, 1);

		User userJax = new User();
		userJax.setFirstName("Jax");
		userJax.setLastName("Teller");
		userJax.setEmail("jax@email.com");
		userJax.setPassword("password");
		userJax.setEnabled(false);
		userJax.setPhotos("photos");
		userJax.addRole(roleAdmin);

		User userSaved = repo.save(userJax);

		assertThat(userSaved.getId() > 0);
	}

	@Test
	public void testCreateUserWithTwoRoles() {

		Role roleAdmin = em.find(Role.class, 1);
		Role roleShipper = em.find(Role.class, 4);

		User userClay = new User();
		userClay.setFirstName("Clay");
		userClay.setLastName("Morrow");
		userClay.setEmail("clay@email.com");
		userClay.setPassword("password");
		userClay.setEnabled(false);
		userClay.setPhotos("photos");
		userClay.addRole(roleAdmin);
		userClay.addRole(roleShipper);

		User userClaySaved = repo.save(userClay);

		assertThat(userClaySaved.getId() > 1);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> result = repo.findAll();
		result.forEach(user -> {
			System.out.println(user);
		});

		assertNotNull(result);
	}

	@Test
	public void testGetUserId() {
		User user = repo.findById(1L).get();
		System.out.println(user);

		assertNotNull(user);
	}

	@Test
	public void testUpdateDetailsUser() {
		User user = repo.findById(1L).get();
		user.setEnabled(true);

		repo.save(user);

		assertNotNull(user);
	}

	@Test
	public void testUpdateRolesUser() {

		User user = repo.findById(1L).get();
		user.getRoles().clear();

		Role roleSalespersonUpdate = em.find(Role.class, 2);

		user.addRole(roleSalespersonUpdate);

		repo.save(user);

		assertNotNull(user);
	}
	
	@Test
	public void testDeleteUser() {
		User user = repo.findById(3L).get();
		
		repo.deleteById(user.getId());
		
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "jax@email.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}

}
