package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class AnswerFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String noSt = request.getParameter("no");
		Long no = Long.parseLong(noSt);
		request.setAttribute("no", no);		
		
		WebUtil.forward("/WEB-INF/views/board/answer.jsp", request, response);

	}

}
