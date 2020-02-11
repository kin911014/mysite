package com.douzone.mysitetest.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.GuestbookRepository;
import com.douzone.mysitetest.vo.GuestbookVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String noSt = request.getParameter("no");
		Long no = Long.parseLong(noSt);
		String password = request.getParameter("password");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		if(new GuestbookRepository().ck(vo)) {
			new GuestbookRepository().delete(vo);
		}
		
		WebUtil.redirect(request.getContextPath() + "/guestbook?a=insertform", request, response);

	}

}
