<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>投稿削除</title>
	</head>
	<body>
		<h1>お悩み掲示板</h1>
		
		<p>
			削除キーを入力し [削除] してください。<br>
			※削除キー未設定の投稿は空欄で削除できます。
		</p>
		
		<c:forEach var="b" items="${boards}">
		    <form action="DeleteServlet" method="post">
		        <input type="hidden" name="boardId" value="${b.id}">
		        削除キー：<input type="password" name="deleteKey"><br>
		        <input type="submit" value="削除">
		    </form>
		</c:forEach>
		
		<br>
		<a href="input">戻る</a>
	</body>
</html>