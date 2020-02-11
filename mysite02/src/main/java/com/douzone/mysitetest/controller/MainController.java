package com.douzone.mysitetest.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.action.main.MainActionFactory;
import com.douzone.mysitetest.web.action.Action;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		String configPath = getServletConfig().getInitParameter("config");
		System.out.println("init() called : " + configPath);
		
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("service() called");
		super.service(req, res);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() called");
		
		String actionName = "a";
		Action action = new MainActionFactory().getAction(actionName);
		action.excute(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() called");

		doGet(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroy() called");
		super.destroy();
	}

}
