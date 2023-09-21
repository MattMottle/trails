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
import org.springframework.web.bind.annotation.PutMapping;

import com.mattmottle.trails.models.Review;
import com.mattmottle.trails.services.ReviewService;
import com.mattmottle.trails.services.TrailService;
import com.mattmottle.trails.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrailService trailService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/trails/{id}/new_review")
	public String newReviewPage(@PathVariable Long id, Model model, @ModelAttribute("newReview") Review newReview) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("loggedUser", userService.findById(userId));
		model.addAttribute("trailToReview", trailService.findById(id));
		return "newReview.jsp";
	}
	
	@PostMapping("/reviews/addReview")
	public String addReview(@Valid @ModelAttribute("newReview") Review newReview, BindingResult result, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		Long trailId = newReview.getReviewedTrail().getId();
		
		if(result.hasErrors()) {
			model.addAttribute("loggedUser", userService.findById(userId));
			model.addAttribute("trailToReview", trailService.findById(trailId));
			return "newReview.jsp";
		}
		reviewService.create(newReview);
		return "redirect:/trails/" + trailId;
	}
	
	@GetMapping("/reviews/{id}/edit_review")
	public String editReview(@PathVariable Long id, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		Review thisReview = reviewService.findById(id);
		model.addAttribute("loggedUser", userService.findById(userId));
		model.addAttribute("thisReview", thisReview);
		return "editReview.jsp";
	}
	
	@PutMapping("/reviews/{id}/editReview")
	public String editReviewInDb(@PathVariable Long id, @Valid @ModelAttribute("thisReview") Review thisReview, BindingResult result, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		if (result.hasErrors()) {
			model.addAttribute("loggedUser", userService.findById(userId));
			model.addAttribute("thisReview", thisReview);
			return "editReview.jsp";
		}
		reviewService.updateReview(thisReview);
		return "redirect:/trails/" + thisReview.getReviewedTrail().getId();
	
	}
	
	@DeleteMapping("/reviews/{id}/deleteReview")
	public String deleteReview(@PathVariable Long id) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		reviewService.deleteReview(id);
		return "redirect:/trails";
	}
}
