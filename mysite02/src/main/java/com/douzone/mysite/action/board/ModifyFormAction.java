package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.BoardRepository;
import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String noSt = request.getParameter("no");
		Long no = Long.parseLong(noSt);
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		// if문 대입하기
		BoardVo views = new BoardRepository().findByNo(vo);
		request.setAttribute("views", views);
		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}
