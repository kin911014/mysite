<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${view.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${view.contents }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
				<!--  완료 후 authUser아래로 넣기 중요중요!!!!!!!!!!!!!!!-->
				<c:choose>
					<c:when test="${authUser.no == view.userNo}">
						<a href="${pageContext.request.contextPath}/board/reply/${view.no}">답글쓰기</a>
						<a href="${pageContext.request.contextPath}/board">글목록</a>
						<a href="${pageContext.request.contextPath}/board/modify/${view.no}">글수정</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/board">글목록</a>
					</c:otherwise>
				</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>