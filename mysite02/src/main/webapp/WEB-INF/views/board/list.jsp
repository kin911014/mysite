<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/board?a=listform"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <select
						name="option">
						<option value="title">제목으로 찾기</option>
						<option value="contents">내용으로 찾기</option>
					</select> <input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach var="write" items="${writes }" varStatus="status">
								
			
					<c:choose>
							<c:when test="${write.depth >= 1 }">
								<tr>
									<td>${write.no }</td>
									<td style="text-align:left; padding-left:${20 * write.depth}px">
										<img src='/mysite02/assets/images/reply.png'> <a
										href="${pageContext.request.contextPath}/board?a=viewform&no=${write.no}">${write.title }</a>
									</td>
									<td>${write.name }</td>
									<td>${write.hit }</td>
									<td>${write.regDate }</td>
									<c:choose>
										<c:when test="${authUser.no == write.userNo }">
										
										<td>
											<a href="${pageContext.request.contextPath}/board?a=listform&no=${write.no}" class="del">삭제</a>
										</td>
										</c:when>
									</c:choose> 
										
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${write.no }</td>
									<td style="text-align:left; padding-left:${20 * write.depth}px"><a
										href="${pageContext.request.contextPath}/board?a=viewform&no=${write.no}">${write.title }</a></td>
									<td>${write.name }</td>
									<td>${write.hit }</td>
									<td>${write.regDate }</td>
											
 									<c:choose>
										<c:when test="${authUser.no == write.userNo }">
										
										<td>
											<a href="${pageContext.request.contextPath}/board?a=listform&no=${write.no}" class="del">삭제</a>
										</td>
										</c:when>
									</c:choose> 
								</tr>
									
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board?a=writeform"
							id="new-book">글쓰기</a>
					</div>
				</c:if>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>