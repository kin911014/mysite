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

public class WriteAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 로그인을 해야 사용 가능!!!
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		Long userNo = authUser.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);
		
		new BoardRepository().insert(vo);
		WebUtil.redirect(request.getContextPath()+"/board?a=listform", request, response);

	}

}
