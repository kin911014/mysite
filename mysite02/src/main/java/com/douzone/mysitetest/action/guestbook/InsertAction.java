package com.douzone.mysitetest.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.GuestbookRepository;
import com.douzone.mysitetest.vo.GuestbookVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class InsertAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String contents = request.getParameter("content");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(contents);
		
		new GuestbookRepository().insert(vo);
		
		WebUtil.redirect(request.getContextPath() + "/guestbook?a=insertform", request, response);

	}

}
