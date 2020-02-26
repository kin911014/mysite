package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute GuestbookVo vo) {
		return "board/list";
	}
	
}
