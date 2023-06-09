package com.business.module.controller;

import com.business.module.config.JwtUtils;
import com.business.module.helper.UserNotFoundException;
import com.business.module.model.JWTRequest;
import com.business.module.model.JWTResponse;
import com.business.module.model.User;
import com.business.module.service.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController
{
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	//generate token

	@PostMapping("/generate-token")
	public ResponseEntity<?> generatetoken(@RequestBody JWTRequest jwtRequest) throws Exception
	{
		try
		{
			System.out.println("----here---");
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		}
		catch (Exception e)
		{
			throw new UserNotFoundException("user not found");
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		System.out.println("---------token--------" + token);
		return ResponseEntity.ok(new JWTResponse(token));
	}


	private void authenticate(String username,String password) throws Exception
	{
		try{

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

		}catch (DisabledException e){
			throw new Exception("user disabled" + e.getMessage());
		}catch (BadCredentialsException e){
			throw new Exception("invalid credential" + e.getMessage());
		}
	}

	//returns the details of current user
	@GetMapping("/current-user")
	public User getCurrentDetails(Principal principal){
		System.out.println("-----------1------------");
         return (User) this.userDetailsService.loadUserByUsername(principal.getName());
	}
}
