package com.shopme.admin.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.admin.role.RoleRepository;
import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role();
		roleAdmin.setName("Admin");
		roleAdmin.setDescription("Manage Everything");
		
		roleRepository.save(roleAdmin);
		
	}
	
	@Test
	public void testCreateRoles() {
		Role roleSalesperson = new Role();
		roleSalesperson.setName("Salesperson");
		roleSalesperson.setDescription("Manage product price, customers, shipping, orders and sales report");
		
		Role roleEditor = new Role();
		roleEditor.setName("Editor");
		roleEditor.setDescription("Manage categories, brands, products, articles and menus");
		
		Role roleShipper = new Role();
		roleShipper.setName("Shipper");
		roleShipper.setDescription("View products, view orders and update order status");
		
		Role roleAssistant = new Role();
		roleAssistant.setName("Assistant");
		roleAssistant.setDescription("Manage questions and reviews");

		roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}

}
