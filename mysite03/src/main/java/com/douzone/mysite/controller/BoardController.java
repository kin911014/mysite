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
import com.douzone.security.Auth;

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
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		System.out.println("ctr vo" + vo);
		UserVo authUser = (UserVo) session.getAttribute("authUser");    
		Long userNo = authUser.getNo();
		
		vo.setUserNo(userNo);
		boardService.insertContents(vo);
		return "redirect:/board";
	}
	////////////////////////////////////////////////////////
	// delete는 접근제어해야한다.
	
	////////////////////////////////////////////////////
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(HttpSession session, @PathVariable("no") Long no, Model model, BoardVo vo  ) {
		vo.setNo(no);
		BoardVo view = boardService.getViewContents(vo);
		model.addAttribute("view", view);
		return "board/view";
	}
	
	////////////////////////////////////////////////////////
	
	// 접근제어 붙이기
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, BoardVo vo, Model model ) {
		vo.setNo(no);
		BoardVo modify = boardService.getModifyContents(vo);
		model.addAttribute("modify", modify);
		return "board/modify";
	}
	
	// 접근제어 붙이기	
	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo vo) {
		System.out.println(no);
		vo.setNo(no);
		System.out.println("ctr1 :"+vo);
		boardService.modifyUpdate(vo);
		System.out.println("ctr last :"+vo);
		return "redirect:/board";
	}
	
	////////////////////////////////////////////////////////
	
	@Auth
	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String answer(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "board/reply";
	}
	
//	@RequestMapping(value="/reply", method=RequestMethod.POST)
//	public String answer(HttpSession session, @PathVariable("no") Long no, BoardVo vo) {
//		vo.setNo(no);
//		BoardVo reply = boardService.replyFindByNo(vo);
//		
//		
//		UserVo authUser = (UserVo) session.getAttribute("authUser");    
//		Long userNo = authUser.getNo();
//		System.out.println(userNo);
//		
//		vo.setUserNo(userNo);
//		System.out.println(vo);
//		boardService.reply(vo);
//		System.out.println(vo);
//		return "board/answer";
//	}
}
