<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>お悩み掲示板</title>
	</head>
	<body>
	
		<h1>お悩み掲示板</h1>
		
		<!-- 投稿フォーム -->
		<form action="ConfirmServlet" method="post">
		    名前：<br>
		    <input type="text" name="name" required><br><br>
		    
		    メール：<br>
		    <input type="email" name="email"><br><br>
		
		    件名：<br>
		    <input type="text" name="subject" required><br><br>
		
		    メッセージ：<br>
		    <textarea name="message" rows="5" cols="40" required></textarea><br><br>
		    
		    削除キー（忘れないでください）：<br>
    		<input type="password" name="deleteKey"><br><br>
		
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
		        
		        <button type="button" onclick="toggleReplyForm(${b.id})">返信する</button>
		        
		        <div id="reply-form-${b.id}" style="display:none;">
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
		        
		        <form action="DeleteconfirmServlet" method="get">
		        	<input type="hidden" name="boardId" value="${b.id}">
		        	<input type="submit" value="削除">
		        </form>
		        
		    </div>
		</c:forEach>
	</body>
</html>