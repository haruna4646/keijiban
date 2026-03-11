<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>投稿内容確認</title>
	</head>
	<body>
	    <h2>投稿内容確認</h2>
	
	    <form action="SubmitServlet" method="post">
	        <p>名前: ${name}</p>
	        <input type="hidden" name="name" value="${name}">
	        
	        <p>メール：${email}</p>
	        <input type="hidden" name="email" value="${email}">
	
	        <p>タイトル: ${title}</p>
	        <input type="hidden" name="title" value="${title}">
	
	        <p>内容:</p>
	        <pre>${content}</pre>
	        <input type="hidden" name="content" value="${content}">
	
	        <button type="button" onclick="history.back();">戻る</button>

	        <button type="submit">送信</button>
	    </form>
	</body>
</html>