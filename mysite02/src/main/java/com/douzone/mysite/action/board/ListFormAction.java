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
		String whereValue = request.getParameter("option"); //title or content?s
		
		// serch에 값을 넣을 경우
		if(search != null) {
			
			// title의 내용을 검색 
			if("title".equals(whereValue)) {
				List<BoardVo> writes = 
						new BoardRepository().titleSearchFindAll(whereValue, search); //title
				
				request.setAttribute("writes", writes);
				WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
				
			// title의 내용을 검색 
			}else if("contents".equals(whereValue)){
				List<BoardVo> writes = 
						new BoardRepository().titleSearchFindAll(whereValue, search); //title
				request.setAttribute("writes", writes);
				WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
			// 검색창에 아무것도 안넣을 경우
			}
			
		// 검색창이 null값이 들어간 경우
		}else {
			List<BoardVo> writes = new BoardRepository().findAll();
			request.setAttribute("writes", writes);
			WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
			
		}
		
	}

}
