package com.yunapi.controller;

import com.yunapi.domain.SecurityAdmin;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/login")
@Controller
public class LoginController {
		
	@RequestMapping(value = "")
	public String loginForm(@AuthenticationPrincipal SecurityAdmin admin){
		if(admin != null) {
			if(admin.getAuthorities() != null) {
				return "redirect:/swagger-ui.html";
			} else
				return "redirect:/logout";
		}
		
		return "view/content/login";
	}

	@ResponseBody
	@RequestMapping(value = "/expired-session")
	public ModelAndView expiredSession(){
		ModelAndView view = new ModelAndView("view/content/redirect_page");
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("msg", "세션이 만료되었습니다.");
		view.addAllObjects(modelMap);
        return view;
	}

	@ResponseBody
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied(){
		ModelAndView view = new ModelAndView("view/content/redirect_page");
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("msg", "접근권한이 없습니다.");
		view.addAllObjects(modelMap);
        return view;
	}
	
}
