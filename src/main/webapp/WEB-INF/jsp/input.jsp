<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>お悩み掲示板</title>
	   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/keijiban.css">
	</head>
	<body>
		<div class="wrapper">
			<h1>お悩み掲示板</h1>
			
			<!-- 投稿フォーム -->
			<form action="ConfirmServlet" method="post">
	
			    <label>名前：</label>
			    <input type="text" name="name">
			
			    <label>メール：</label>
			    <input type="email" name="email">
			
			    <label>件名：</label>
			    <input type="text" name="subject">
				
				<br>
				
			    <label>メッセージ：</label>
			    <textarea name="message" rows="5"></textarea>
			
			    <label>削除キー（忘れないでください）：</label>
			    <input type="password" name="deleteKey">
			
			    <input type="submit" value="投稿する">
			    <input type="reset" value="リセット">
			</form>
			
			<hr>
			
			<h2>投稿一覧</h2>
			
			<c:forEach var="b" items="${boards}">
			    <div class="board">
			        <strong>名前：</strong>${b.name}<br>
			        <strong>日時：</strong>${b.createdAt}<br>
			        <strong>件名：</strong>${b.subject}<br>
			        <strong>メッセージ：</strong><br>
			        <p>${b.message}</p>
			
			        <!-- 投稿への返信 -->
			        <c:forEach var="r" items="${b.replies}">
			            <div class="reply">
			                <strong>返信者：</strong>${r.name}<br>
			                <strong>メッセージ：</strong><br>
			                <p>${r.message}</p>
			                <strong>日時：</strong>${r.createdAt}<br>
			            </div>
			        </c:forEach>
			        
			        <div class="board-buttons">
					    <button type="button" onclick="toggleReplyForm(${b.id})">返信する</button>
					    
					    <form action="DeleteconfirmServlet" method="get" class="delete-form">
					        <input type="hidden" name="boardId" value="${b.id}">
					        <input type="submit" value="削除">
					    </form>
					</div>
					
					<div id="reply-form-${b.id}" style="display:none; margin-top:10px;">
					    <form action="ReplyServlet" method="post">
					        <input type="hidden" name="boardId" value="${b.id}">
					        
					        名前：<br>
					        <input type="text" name="name" required><br>
					        
					        メッセージ：<br>
					        <textarea name="message" rows="3" cols="30" required></textarea>
					        
					        <input type="submit" value="返信する">
					    </form>
					</div>
			        
			        <script>
						function toggleReplyForm(boardId) {
						    const form = document.getElementById("reply-form-" + boardId);
						    if (form.style.display === "none") {
						        form.style.display = "block";
						    } else {
						        form.style.display = "none";
						    }
						}
					</script>
			    </div>
			</c:forEach>
		</div>
	</body>
</html>