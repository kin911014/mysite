package com.douzone.mysitetest.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class LogoutAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("authUser") != null) {
			/* 로그아웃 처리*/
			session.removeAttribute("authUser");
			session.invalidate();
		}
		WebUtil.redirect(request.getContextPath(), request, response);
	}

}
