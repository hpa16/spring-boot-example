package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
	//@Autowired
	//private SecurityService securityService

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration()
	{
		return "registrationPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String error, String logout)
	{
		if (error != null)
			return "Your username and password is invalid.";

		if (logout != null)
			return "You have been logged out successfully.";

		return "loginPage";
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome()
	{
		return "welcomePage";
	}
}