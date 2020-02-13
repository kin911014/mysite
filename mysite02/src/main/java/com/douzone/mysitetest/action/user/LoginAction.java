package com.douzone.mysitetest.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysitetest.repository.UserRepository;
import com.douzone.mysitetest.vo.UserVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class LoginAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		// userVo의 no와 name 리턴
		UserVo authUser = new UserRepository().findByEmailAndPassword(vo);
		if(authUser == null) {
			/*로그인 실패*/
			request.setAttribute("result", "fail");
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp?a=loginform", request, response);
			return;
		}
		/* 로그인 처리*/
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		// request.getContextPath()는 /mysite02이며, redirect에서 내 소스의 정보가 유출되면 안되기때문에 아래와 같이 쓸 것
		WebUtil.redirect(request.getContextPath(), request, response);
		

	}

}
