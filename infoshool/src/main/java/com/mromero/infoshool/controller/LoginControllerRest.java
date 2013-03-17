package com.mromero.infoshool.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/schoolrest/**")
public class LoginControllerRest { 

	private static final Logger logger = LoggerFactory.getLogger(LoginControllerRest.class);

	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public @ResponseBody String printWelcome(ModelMap model, Principal principal ) {


		return getStatus();

	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public @ResponseBody String login(ModelMap model) {

		return "EN LOGIN";

	}

	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public @ResponseBody String loginerror(ModelMap model) {


		return getStatus();

	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public @ResponseBody String logout(ModelMap model) {

		return getStatus();

	}

	@RequestMapping(value="/getstatus", method = RequestMethod.GET)
	public @ResponseBody String getStatus() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")){
			logger.info("authenticated " + auth.getName());
			return "authenticated";
		} else {
			logger.info("NO_authenticated");
			return "no_authenticated";
		}
	}
}