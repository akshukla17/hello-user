package com.todo.hellouser.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.hellouser.exception.BadRequestException;
import com.todo.hellouser.exception.NoDataFoundException;
import com.todo.hellouser.model.ValidEmail;
import com.todo.hellouser.service.HelloUserService;

@RestController
public class HelloUserController {
	
	Logger logger = LoggerFactory.getLogger(HelloUserController.class);

	@Autowired
	private HelloUserService userService;

	/**
	 * search user using username
	 * 
	 * @param username
	 * @return
	 * @throws URISyntaxException
	 */
	@GetMapping("/users")
	public ValidEmail findByUsername(@RequestParam(name = "username", required = true) String username)
			throws URISyntaxException {

		if (StringUtils.isEmpty(username)) {
			throw new BadRequestException("username should not be empty or null");
		}
		List<String> validEmails = userService.findByUserName(username);
		if (validEmails.isEmpty()) {
			throw new NoDataFoundException("No valid emails are found for user" + username);
		}
		logger.info("returning list of valid emails from comments of the user "+username+"'s posts");
		return new ValidEmail(validEmails);
	}
}
