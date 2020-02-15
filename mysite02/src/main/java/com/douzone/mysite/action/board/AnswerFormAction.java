package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysitetest.repository.BoardRepository;
import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.web.action.Action;
import com.douzone.mysitetest.web.util.WebUtil;

public class AnswerFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// no 파라미터 변수 설정
		String noSt = request.getParameter("no");
		Long no = Long.parseLong(noSt);
		
		// boardVo에 value값 설정
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		// if문 대입하기
		BoardVo answer = new BoardRepository().findByNo(vo);
		request.setAttribute("answer", answer);
		WebUtil.forward("/WEB-INF/views/board/answer.jsp", request, response);

	}

}
