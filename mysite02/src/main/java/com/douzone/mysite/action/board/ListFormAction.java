/**
 * 
 */
package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.BoardRepository;
import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class ListFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String search = request.getParameter("kwd");//사용자 입력값
		String whereValue = request.getParameter("option"); //title or contens
		
		System.out.println(search);
		System.out.println(whereValue);
		
		if("title".equals(whereValue) && search != null) {
			List<BoardVo> writes = 
					new BoardRepository().searchFindAll(search); //title
		
			request.setAttribute("writes", writes);
			System.out.println("1번if실행");
			WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
		
		}else {
			
			List<BoardVo> writes = new BoardRepository().findAll();
			request.setAttribute("writes", writes);
			System.out.println("2번if실행");
			WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
		}
		
//		if(search != null) {
//				return;
//			}else {
//				List<BoardVo> writes = new BoardRepository().findAll();
//				request.setAttribute("writes", writes);
//				System.out.println("3번if실행");
//				WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
//			}
			
//			if("title".equals(whereValue)) {
//				List<BoardVo> writes = 
//						new BoardRepository().searchFindAll(whereValue,search); //title
//				request.setAttribute("writes", writes);
//				System.out.println("1번if실행");
//				WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
//				
//			}else if("contents".equals(whereValue)) {
//				List<BoardVo> writes = new BoardRepository().searchFindAll(whereValue,search); //contents
//				request.setAttribute("writes", writes);
//				System.out.println("2번if실행");
//				WebUtil.forward("/EB-INF/views/board/list.jsp", request, response);
//			}
			
			
//		}else {
			
//		}

	}

}
