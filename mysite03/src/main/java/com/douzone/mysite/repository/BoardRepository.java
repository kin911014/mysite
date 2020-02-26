package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll(){
		List<BoardVo> result = sqlSession.selectList("board.findAll");
		return result;
	}	
	
	
	public void insertContents(BoardVo vo) {
		sqlSession.insert("board.insertContents", vo);
		
	}
	
	public BoardVo findByNo(BoardVo vo) {
		return sqlSession.selectOne("board.findByNo", vo);
		
	}
	
	public List<BoardVo> titleSearchFindAll(String whereValue, String search){
		List<BoardVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		String sql = null;
		try {
			conn = getConnection();
			
			if("title".equals(whereValue)) {
				 sql ="select a.no,\r\n" + 
						" a.title,\r\n" + 
						" a.contents,\r\n" + 
						" a.hit,\r\n" + 
						" a.reg_date,\r\n" + 
						" a.g_no,\r\n" + 
						" a.o_no,\r\n" + 
						" a.depth,\r\n" + 
						" a.user_no,\r\n" + 
						" b.name\r\n" + 
						"from board a, user b\r\n" + 
						"where title like ?\r\n" + 
						"order by a.g_no desc, a.o_no asc" ;
				
				
			}else if("contents".equals(whereValue)) {
				 sql ="select a.no,\r\n" + 
						" a.title,\r\n" + 
						" a.contents,\r\n" + 
						" a.hit,\r\n" + 
						" a.reg_date,\r\n" + 
						" a.g_no,\r\n" + 
						" a.o_no,\r\n" + 
						" a.depth,\r\n" + 
						" a.user_no,\r\n" + 
						" b.name\r\n" + 
						"from board a, user b\r\n" + 
						"where contents like ?\r\n" + 
						"order by a.g_no desc, a.o_no asc" ;
			}
	
			pstmt = conn.prepareStatement(sql);
			// 강사님께 질문하기 
			pstmt.setString(1, "%"+search+"%");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			System.out.println("1번오류");
		
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
				System.out.println("result.add 오류 ");
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
	
//	public List<BoardVo> contentsSearchFindAll(String search){
//		List<BoardVo> result = new ArrayList<>();
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;	
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			
//			
//			String sql ="select a.no,\r\n" + 
//					" a.title,\r\n" + 
//					" a.contents,\r\n" + 
//					" a.hit,\r\n" + 
//					" a.reg_date,\r\n" + 
//					" a.g_no,\r\n" + 
//					" a.o_no,\r\n" + 
//					" a.depth,\r\n" + 
//					" a.user_no,\r\n" + 
//					" b.name\r\n" + 
//					"from board a, user b\r\n" + 
//					"where a.contents like ?\r\n" + 
//					"order by a.g_no desc, a.o_no asc" ;
//		
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%"+search+"%");
//			rs = pstmt.executeQuery();
//			System.out.println("1번오류");
//		
//		// 5. 결과 가져오기
//			while(rs.next()) {
//				// ()안은 인덱스를 가져오는게 좋다.
//				Long no = rs.getLong(1);
//				String title = rs.getString(2);
//				String contents = rs.getString(3);
//				int hit = rs.getInt(4);
//				String regDate = rs.getString(5);
//				int gNo = rs.getInt(6);
//				int oNo = rs.getInt(7);
//				int depth = rs.getInt(8);
//				Long userNo = rs.getLong(9);
//				String name = rs.getString(10);
//				
//				BoardVo vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setContents(contents);
//				vo.setHit(hit);
//				vo.setRegDate(regDate);
//				
//				vo.setgNo(gNo);
//				vo.setoNo(oNo);
//				vo.setDepth(depth);
//				vo.setUserNo(userNo);
//				vo.setName(name);
//				
//				result.add(vo);
//				System.out.println("result.add 오류 ");
//			}
//		} catch (SQLException e) {
//			System.out.println("error : " + e);
//		}finally {
//			// 6. 자원정리
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}	
	
	
	public int AnswerInsert(BoardVo vo) {

		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
										
			String sql = "insert \r\n" + 
					" into board values(\r\n" + 
					" null, \r\n" + 
					" ?, \r\n" +  // 1. title
					" ?, \r\n" +  // 2. contents
					" 0, \r\n" +  // 조회수
					" now(),\r\n" + 
					" ?,\r\n" +  // 3. g_no에서 가져옴
					" ?+1,\r\n" +  // 4. o_no+1로 가져옴
					" ?+1,\r\n" +// 5. depth
					" ?)" ;      // 6. userno
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getgNo()); 
			pstmt.setInt(4, vo.getoNo());
			pstmt.setInt(5, vo.getDepth()); 
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
	
	
	public void update(BoardVo vo) {
		System.out.println("ctr1 :"+vo);
		sqlSession.update("board.update", vo);
	}
	
	public void deleteList(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mysql://192.168.1.105:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드러이버 로딩 실패:" + e);
		}

		return conn;
	}
}
