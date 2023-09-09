package com.mattmottle.trails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mattmottle.trails.models.LoginUser;
import com.mattmottle.trails.models.User;
import com.mattmottle.trails.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String loginRegPage(@ModelAttribute("registerUser") User registerUser, 
		@ModelAttribute("loginUser") LoginUser loginUser) {
		return "logAndReg.jsp";
	}
	@PostMapping("/register")
	public String processRegistraion(@Valid @ModelAttribute("registerUser") User registerUser, 
			BindingResult result, @ModelAttribute("loginUser") LoginUser loginUser, HttpSession session) {
		// perform the additional validations and add to db if validations are good
		User theNewUser = userService.register(registerUser, result);
		if(result.hasErrors()){
			return "logAndReg.jsp";
		}else {
			// save the id of the logged in user in session
			session.setAttribute("userId", theNewUser.getId());
			return "redirect:/trails";
		}
	}
	
	@PostMapping("/login")
	public String processLogin(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result,
			@ModelAttribute("registerUser") User registerUser, HttpSession session) {
		User foundUser = userService.login(loginUser, result);
		if(result.hasErrors() ) {
			return "logAndReg.jsp";
		} else {
		session.setAttribute("userId", foundUser.getId());
		return "redirect:/trails";	
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
