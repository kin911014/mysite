package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class SiteController {
	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String main(Model model) {
		SiteVo siteVo = siteService.findAll();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String main(SiteVo siteVo) {
//		siteService.upload(siteVo);
		return "redirect:/main";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
