package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.BoardRepository;
import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class ViewFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String noSt = request.getParameter("no");
		Long no = Long.parseLong(noSt);
		request.setAttribute("no", no);
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);

	}

}
