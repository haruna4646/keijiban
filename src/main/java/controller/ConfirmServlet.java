package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConfirmServlet")
public class ConfirmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //値を取得
        String name = request.getParameter("name");
        String email = request.getParameter("email"); 
        String title = request.getParameter("subject");
        String content = request.getParameter("message");
        String deleteKey = request.getParameter("deleteKey");
        
        //削除キー未設定の場合nullにする
        if(deleteKey != null && deleteKey.isEmpty()) {
        	deleteKey = null;
        }
        
        //リクエスト属性としてセット
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.setAttribute("deleteKey", deleteKey);

        //フォワード
        request.getRequestDispatcher("/WEB-INF/jsp/confirm.jsp").forward(request, response);
    }
}
