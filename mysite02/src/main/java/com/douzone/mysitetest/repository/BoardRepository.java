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
				" a.g_no,\r\n" + 
				" a.o_no,\r\n" + 
				" a.depth,\r\n" + 
				" a.user_no,\r\n" + 
				" b.name\r\n" + 
				" from board a, user b\r\n" + 
				" where a.user_no = b.no\r\n" +
				" order by a.g_no desc, a.o_no asc"; 
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
										
			String sql = "insert \r\n" + 
					" into board values(\r\n" + 
					" null, \r\n" + 
					" ?, \r\n" + 
					" ?, \r\n" + 
					" 0, \r\n" + 
					" now(),\r\n" + 
					" (select ifnull(max(b.g_no),0)+1 from board b),\r\n" + 
					" 1,\r\n" + 
					" 0,\r\n" + 
					" ?)" ;
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

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
	
	public int AnswerInsert(BoardVo vo) {

		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
										
			String sql = "insert \r\n" + 
					" into board values(\r\n" + 
					" null, \r\n" + 
					" ?, \r\n" +  // title
					" ?, \r\n" +  // contents
					" 0, \r\n" +  // 조회수
					" now(),\r\n" + 
					" ?,\r\n" +  // g_no에서 가져옴
					" 1,\r\n" +  // o_no+1로 가져옴
					" 0,\r\n" +  
					" ?)" ;      // userno
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getgNo()); 
			pstmt.setLong(3, vo.getUserNo());

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
	
	public BoardVo findByNo(BoardVo vo) {
		BoardVo boardVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select a.title, a.contents, a.no, a.user_no\r\n" + 
					"from board a, user b\r\n" + 
					"where a.no = ?\r\n" + 
					"and a.user_no = b.no";
			pstmt = conn.prepareStatement(sql);
			
			// BoardVo vo에서 no받은 뒤 그 넘버의 title과 contents를 보내기
			// 아래 1이 ?에 들어가면 해당 title과 contents를 받을 수 있다.
			pstmt.setLong(1, vo.getNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long no = rs.getLong(3);
				Long userNo = rs.getLong(4);

				boardVo = new BoardVo();
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setNo(no);
				boardVo.setUserNo(userNo);
				
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
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

		return boardVo;
	}
	
	public int update(BoardVo vo) {
		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "update board\r\n" + 
					" set title = ?,\r\n" + 
					" contents = ?\r\n" + 
					" where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// BoardVo vo에서 no받은 뒤 그 넘버의 title과 contents를 보내기
			// 아래 1이 ?에 들어가면 해당 title과 contents를 받을 수 있다.
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
			count = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
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
