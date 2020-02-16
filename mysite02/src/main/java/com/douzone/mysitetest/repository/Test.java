//package com.douzone.mysitetest.repository;
//
//public BoardVo findByNo(BoardVo vo) {
//	BoardVo boardVo = null;
//
//	Connection conn = null;
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//
//	try {
//		conn = getConnection();
//		String sql = " select a.no, a.title, a.contents, a.hit, a.g_no, a.o_no, a.depth, a.user_no\r\n" + 
//				" from board a, user b\r\n" + 
//				" where a.no = ?\r\n" + 
//				" and a.user_no = b.no";
//		pstmt = conn.prepareStatement(sql);
//		
//		// BoardVo vo에서 no받은 뒤 그 넘버의 title과 contents를 보내기
//		// 아래 1이 ?에 들어가면 해당 title과 contents를 받을 수 있다.
//		pstmt.setLong(1, vo.getNo());
//		rs = pstmt.executeQuery();
//
//		if (rs.next()) {
//			Long no = rs.getLong(1);
//			String title = rs.getString(2);
//			String contents = rs.getString(3);
//			int hit = rs.getInt(4);
//			int gNo = rs.getInt(5);
//			int oNo = rs.getInt(6);
//			int depth = rs.getInt(7);
//			Long userNo = rs.getLong(8);
//
//			boardVo = new BoardVo();
//			boardVo.setNo(no);
//			boardVo.setTitle(title);
//			boardVo.setContents(contents);
//			boardVo.setHit(hit);
//			boardVo.setgNo(gNo);
//			boardVo.setoNo(oNo);
//			boardVo.setDepth(depth);
//			boardVo.setUserNo(userNo);
//			
//		}
//	} catch (SQLException e) {
//		System.out.println("error :" + e);
//	} finally {
//		// 자원 정리
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	return boardVo;
//	}		
//}