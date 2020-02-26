package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(Model model) {
		List<BoardVo> lists= boardService.findAll();
		model.addAttribute("lists", lists);
		return "board/list";
	}
	
	//////////////////////////////////////////////////////
	
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");    
		Long userNo = authUser.getNo();
		
		vo.setUserNo(userNo);
		boardService.insertContents(vo);
		return "redirect:/board";
	}
	
	
	////////////////////////////////////////////////////
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model, BoardVo vo  ) {
		vo.setNo(no);
		BoardVo view = boardService.getViewContents(vo);
		model.addAttribute("view", view);
		return "board/view";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.POST)
	public String view(BoardVo boardVo) {
		return "board/view";
	}
	
	////////////////////////////////////////////////////////
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify() {
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(BoardVo boardVo) {
		return "board/modify";
	}
	
	////////////////////////////////////////////////////////
	
	@RequestMapping(value="/answer", method=RequestMethod.GET)
	public String answer() {
		return "board/answer";
	}
	
	@RequestMapping(value="/answer", method=RequestMethod.POST)
	public String answer(BoardVo boardVo) {
		return "board/answer";
	}
}
