package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.BoardRepository;
import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.vo.UserVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class AnswerAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		
		// title파라미터
		String title = request.getParameter("title");
		
		// contents파라미터
		String content = request.getParameter("content");
		
		// g_no파라미터
		String gNoSt = request.getParameter("gNo");
		int gNo = Integer.parseInt(gNoSt);
		
		// o_no파라미터
		String oNoSt = request.getParameter("oNo");
		int oNo = Integer.parseInt(oNoSt);
		
		// depth파라미터
		String depthSt= request.getParameter("depth");
		int depth = Integer.parseInt(depthSt);
		
		// user_no파라미터
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		Long userNo = authUser.getNo();
		
		
		// 로그인을 해야 사용 가능!!!
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setgNo(gNo);
		vo.setoNo(oNo);
		vo.setDepth(depth);
		vo.setUserNo(userNo);
		
		new BoardRepository().AnswerInsert(vo);
		WebUtil.redirect(request.getContextPath()+"/board?a=listform", request, response);
	}

}
