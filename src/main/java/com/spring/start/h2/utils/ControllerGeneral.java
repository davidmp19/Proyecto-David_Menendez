package com.spring.start.h2.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerGeneral {
	
	
	 	@GetMapping("/login")
	    public ModelAndView getLogin() {
	    	ModelAndView model = new ModelAndView();
	    	model.setViewName("htmlsGenerales/login");
	        return model;
	    }
	    
	    @GetMapping("/logout")
	    public ModelAndView getLogOut() {
	    	ModelAndView model = new ModelAndView();
	    	model.setViewName("index");
	        return model;
	    }
}
