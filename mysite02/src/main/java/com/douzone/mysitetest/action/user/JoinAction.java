package com.douzone.mysitetest.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.UserRepository;
import com.douzone.mysitetest.vo.UserVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class JoinAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		new UserRepository().insert(vo);
		
		WebUtil.redirect(request.getContextPath()+"/user?a=joinsuccess", request, response);

	}

}
