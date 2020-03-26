package com.douzone.mysite.repository;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo findAll(){
		SiteVo result = sqlSession.selectOne("site.findAll");
		return result;
	}


	public void upload(SiteVo siteVo) {
		sqlSession.insert("site.upload", siteVo);
	}		
}
