<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>お悩み掲示板</title>
	</head>
	<body>
	
		<h2>お悩み掲示板</h2>
		
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
		
		    <input type="submit" value="投稿する">
		    <input type="reset" value="リセット">
		</form>
		
		<hr>
		
		<h2>投稿一覧（新しい順）</h2>
		
		<c:forEach var="b" items="${boards}">
		    <div class="board">
		        <strong>件名：</strong>${b.subject}<br>
		        <strong>名前：</strong>${b.name}<br>
		        <strong>日時：</strong>${b.createdAt}<br>
		        <p>${b.message}</p>
		
		        <!-- 投稿への返信 -->
		        <c:forEach var="r" items="${b.replies}">
		            <div class="reply">
		                <strong>返信者：</strong>${r.name}<br>
		                <strong>日時：</strong>${r.createdAt}<br>
		                <p>${r.message}</p>
		            </div>
		        </c:forEach>
		    </div>
		</c:forEach>
		
	</body>
</html>