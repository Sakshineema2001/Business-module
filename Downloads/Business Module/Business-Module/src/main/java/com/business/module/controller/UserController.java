package com.business.module.controller;

import com.business.module.model.Role;
import com.business.module.model.User;
import com.business.module.model.UserRoles;
import com.business.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController
{
	@Autowired
    private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

		Set<UserRoles> userRole = new HashSet<>();
		Role role = new Role();
		role.setRoleId(13l);
		role.setRoleName("Normal");

		UserRoles userRoles = new UserRoles();
		userRoles.setRole(role);
		userRoles.setUser(user);
		userRole.add(userRoles);
		return userService.createUser(user,userRole);
	}

	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username){
		return this.userService.getUserName(username);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id){
		userService.deleteUser(id);
	}

	@GetMapping("/getAll")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
}
