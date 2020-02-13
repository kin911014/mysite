package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.BoardRepository;
import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String noSt = request.getParameter("viewno");
//		request.setAttribute("no", no);
		System.out.println(noSt + "에러");
		Long no = Long.parseLong(noSt);

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setNo(no);

		new BoardRepository().update(vo);
		WebUtil.redirect(request.getContextPath() + "board?a=viewform", request, response);

	}

}
