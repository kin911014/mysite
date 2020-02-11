package com.douzone.mysitetest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.GuestbookRepository;
import com.douzone.mysitetest.vo.GuestbookVo;

public class GuestbookCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("a");

		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String contents = request.getParameter("message");
			String password = request.getParameter("password");
			
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setContents(contents);
			vo.setPassword(password);
			
			new GuestbookRepository().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/gb");
		} else if ("deleteform".equals(action)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
			
		} else if ("delete".equals(action)) {
			
			String noSt = request.getParameter("no");
			Long no = Long.parseLong(noSt);
			String password = request.getParameter("password");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			if(new GuestbookRepository().ck(vo)){
			new GuestbookRepository().delete(vo);	
			}
			response.sendRedirect(request.getContextPath() +"/gb");
		} else {
			
			List<GuestbookVo> list = new GuestbookRepository().findAll();
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
			
		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
