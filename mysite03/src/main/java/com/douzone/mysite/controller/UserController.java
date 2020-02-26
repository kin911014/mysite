package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	// jps에서 post로 값을 보내면 vo에 담긴다.
	public String join(UserVo vo) {
		System.out.println(vo);
		userService.join(vo);
		System.out.println(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	//@ModelAttribute UserVo vo에서 UserVo를 어트리뷰트해서 jsp에 넘어간다. 그리고 앞글자는 userVo로 쓸 수 있다.
	public String login(HttpSession session,@ModelAttribute UserVo vo) {
		UserVo authUser = userService.getUser(vo);
		if(authUser == null) {
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		//////////////////////접근제어////////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////
		
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		////////////////////////접근제어///////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");	
		if(authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////
		
		Long no = authUser.getNo();
		
		UserVo vo = userService.getUser(no);
		
		model.addAttribute("userVo", vo);
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo) {
		
		////////////////////////접근제어///////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");	
		if(authUser == null) {
			return "redirect:/";
		}
		/////////////////////////////////////////////////////////////
		
		return "redirect:/user/update";
	}
	
//	// 모든 exception은 이안으로 들어오라는 로직
//	@ExceptionHandler( Exception.class)
//	public String handleException() {
//		return "error/exception";
//	}
}
