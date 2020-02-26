package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

// 이렇게 어노테이션을 만들어주면 rootapplication context에 만들어진다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository; 

	public boolean join(UserVo vo) {
		int count = userRepository.insert(vo);
		return count == 1;
		
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
		 
		
		
	}

	public UserVo getUser(Long no) {
		return userRepository.find(no);
		
	}
	
	
}