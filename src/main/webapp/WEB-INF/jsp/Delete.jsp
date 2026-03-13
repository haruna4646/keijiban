<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>投稿削除</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/keijiban.css">
</head>
<body>
    <h1>お悩み掲示板</h1>

    <p>
        削除キーを入力し [削除] してください。<br>
        ※削除キー未設定の投稿は空欄で削除できます。
    </p>

    <!-- エラーメッセージ -->
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <!-- 成功メッセージ -->
    <c:if test="${not empty message}">
        <p style="color:green">${message}</p>
    </c:if>

    <form action="DeleteServlet" method="post">
        <!-- ★ DeleteconfirmServlet から来た boardId -->
        <input type="hidden" name="boardId" value="${boardId}">
        削除キー：<input type="password" name="deleteKey"><br>
        <input type="submit" value="削除">
    </form>

    <br>
    <a href="input">戻る</a>
</body>
</html>