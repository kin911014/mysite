package com.douzone.mysitetest.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.GuestbookRepository;
import com.douzone.mysitetest.vo.GuestbookVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class InsertFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<GuestbookVo> list = new GuestbookRepository().findAll();
		request.setAttribute("list", list);
		WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
	}

}
