package com.douzone.mysitetest.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysitetest.vo.BoardVo;
import com.douzone.mysitetest.vo.UserVo;

public class BoardRepository {

	public List<BoardVo> findAll(){
		List<BoardVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = getConnection();
		
		String sql = "select a.no,\r\n" + 
				" a.title,\r\n" + 
				" a.contents,\r\n" + 
				" a.hit,\r\n" + 
				" a.reg_date,\r\n" + 
				" max(a.g_no)+1,\r\n" + 
				" a.o_no,\r\n" + 
				" a.depth,\r\n" + 
				" a.user_no,\r\n" + 
				" b.name\r\n" + 
				"from board a, user b\r\n" + 
				"where a.user_no = b.no\r\n" + 
				"order by a.g_no desc, a.o_no asc"; 
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		// 5. 결과 가져오기
		while(rs.next()) {
			// ()안은 인덱스를 가져오는게 좋다.
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			String contents = rs.getString(3);
			int hit = rs.getInt(4);
			String regDate = rs.getString(5);
			int gNo = rs.getInt(6);
			int oNo = rs.getInt(7);
			int depth = rs.getInt(8);
			Long userNo = rs.getLong(9);
			String name = rs.getString(10);
			
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
			vo.setUserNo(userNo);
			vo.setName(name);
			
			result.add(vo);
		}
		
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}finally {
			// 6. 자원정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}	
	
	public int insert(BoardVo vo) {

		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
										//'취미', '코딩입니다', 0, now(), 1, 3, 2, 2
			String sql = "insert into board values(null, ?, ?, ?, now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setInt(4, vo.getgNo());
			pstmt.setInt(5, vo.getoNo());
			pstmt.setLong(6, vo.getUserNo());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드러이버 로딩 실패:" + e);
		}

		return conn;
	}
}
