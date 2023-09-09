package com.mattmottle.trails.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mattmottle.trails.models.Trail;
import com.mattmottle.trails.models.User;
import com.mattmottle.trails.services.TrailService;
import com.mattmottle.trails.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TrailController {
	@Autowired
	private TrailService trailService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/trails")
	public String dashboard(HttpSession session, Model viewModel, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		// talk to service to grab user by the saved id in session
		viewModel.addAttribute("loggedUser", userService.findById(userId));
		model.addAttribute("trails", trailService.findAll());
		return "dashboard.jsp";
	}
	
	@GetMapping("/trails/{trailId}")
	public String oneTrail(Model model, @PathVariable("trailId") Long trailId, HttpSession session, Model viewModel) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		viewModel.addAttribute("loggedUser", userService.findById(userId));
		Trail trail = trailService.findById(trailId);
		model.addAttribute("trail", trail);
		return "trail.jsp";
	}
	@GetMapping("/trails/new")
	public String newTrail(@ModelAttribute("trail") Trail trail, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		User user = userService.findById((Long)session.getAttribute("userId"));
		model.addAttribute("user", user);
		return "newTrail.jsp";
	}
	@PostMapping("/trails/new/process")
	public String createTrail(Model model, @Valid @ModelAttribute("trail") Trail trail, BindingResult result) {
		if( result.hasErrors()) {
			return "newTrail.jsp";
		} else {
			trailService.create(trail);
			return "redirect:/trails";
		}
	}
	@GetMapping("/trails/{trailId}/edit")
	public String editTrail(@PathVariable("trailId") Long trailId, HttpSession session, Model viewModel, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		viewModel.addAttribute("loggedUser", userService.findById(userId));
		Trail trail = trailService.findById(trailId);
		model.addAttribute("trail", trail);
		
		return "editTrail.jsp";
	}
	@RequestMapping(value="/trails/{trailId}/edit/process", method=RequestMethod.PUT)
	public String updateTrail(@Valid @ModelAttribute("trail") Trail trail, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("trail", trail);
			return "editTrail.jsp";
		} else {
			trailService.updateTrail(trail);
			return "redirect:/trails";
		}
	}
	@DeleteMapping("/{trailId}/delete")
    public String deleteTrail(@PathVariable("trailId") Long trailId) {
    	trailService.deleteTrail(trailId);
    	return "redirect:/trails";
    }
}
