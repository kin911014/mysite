<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

var startNo = 0;
var isEnd = false;
var render = function(vo, mode){
	var html = 
		"<li data-no='" + vo.no + "'>" + 
		 "<strong>"+ vo.name +"</strong>" +
		 "<p>" + vo.contents.replace("/\n/gi", "<br>") + "</p>" +
		 "<strong></strong>" +
		 "<a href='' data-no='" + vo.no + "'>삭제</a>" +
		 "</li>";
	if(mode){
		$("#list-guestbook").prepend(html);
	}else{
		$("#list-guestbook").append(html);
	}
}

var fetchList = function(){
	if(isEnd){
		return;
	}
	$.ajax({
		url: '${pageContext.request.contextPath }/api/guestbook/list/' + startNo,
		async: true,
		type: 'get',
		dataType: 'json',
		data: '',
		success: function(response){
			console.log(response);
			if(response.result != "success")	{
				console.error(response.message);
				return;
			}
			
			// detect end
			if(response.data.length == 0){
				isEnd = true;
				$('.btn-fetch').prop("disabled", true);
				return;
			}
			
			// rendering
			$.each(response.data, function(index, vo){
				render(vo);
				
			});
			startNo = $('#list-guestbook li').last().data('no') || 0;
		},
		error: function(xhr, status, e){
			console.error(status + ":" + e);
		}
	});
}

$(function(){
	// 가져오기 버튼 Click 이벤트
	$('.btn-fetch').click(fetchList);
	
	// 입력폼 submit 이벤트
	$('#add-form').submit(function(event){
		event.preventDefault();
		
		var vo = {};
		vo.name = $("#input-name").val();
		vo.password = $("#input-password").val();
		vo.contents = $("#tx-content").val();
		
		$.ajax({
			url: '${pageContext.request.contextPath }/api/guestbook/add',
			async: true,
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
				// rendering
				render(response.data, true);
				
				// form reset
				$('#add-form')[0].reset();
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});

	});

		// 창 스크롤 이벤트
		$(window).scroll(function() {
			var $window = $(this);
			var windowHeight = $window.height();

			var scrollTop = $window.scrollTop();
			var documentHeight = $(document).height();
			if (scrollTop + windowHeight + 10 > documentHeight) {
				fetchList();
			}
		});

		// 처음 리스트 가져오기
		fetchList();
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				
				<div style='margin:20px 0 0 0'>
					<button class='btn-fetch'>다음 가져오기</button>
				</div>
				
				<ul id="list-guestbook">
				</ul>
				
				<div style='margin:20px 0 0 0'>
					<button class='btn-fetch'>다음 가져오기</button>
				</div>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>