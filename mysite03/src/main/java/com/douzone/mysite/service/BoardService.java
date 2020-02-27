package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> findAll() {
		List<BoardVo> serviceList = boardRepository.findAll();
		return serviceList;
	}

	public void insertContents(BoardVo vo) {
		boardRepository.insertContents(vo);
	}

	public BoardVo getViewContents(BoardVo vo) {
		return boardRepository.findByNo(vo);
	}

	public BoardVo getModifyContents(BoardVo vo) {
		return boardRepository.findByNo(vo);
	}

	public void modifyUpdate(BoardVo vo) {
		boardRepository.update(vo);
		
	}

	public void reply(BoardVo vo) {
		System.out.println("service :"+vo);
		boardRepository.ReplyInsert(vo);
		
	}

	public BoardVo replyFindByNo(BoardVo vo) {
		return boardRepository.findByNo(vo);
		
	}
}
