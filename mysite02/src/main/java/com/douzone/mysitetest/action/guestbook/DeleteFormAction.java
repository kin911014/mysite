package com.douzone.mysitetest.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);

	}

}
