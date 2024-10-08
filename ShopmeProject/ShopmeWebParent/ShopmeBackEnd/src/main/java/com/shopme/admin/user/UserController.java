package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String findAll(Model model) {
		List<User> users = service.findAll();
		model.addAttribute("users", users);

		return "users";
	}

	@GetMapping("/users/new")
	public String newUser(Model model) {
		User user = new User();
		List<Role> listRoles = service.findRoles();

		user.setEnabled(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);

		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		service.save(user);
		
		redirectAttributes.addFlashAttribute("message", "Usuário salvo com sucesso!");
		
		return "redirect:/users";
	}

}
