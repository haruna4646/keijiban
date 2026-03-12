package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        //文字列をint型に変換
        String boardIdStr = request.getParameter("boardId");
        
        if(boardIdStr == null || boardIdStr.isEmpty()) {
        	throw new ServletException("boardIdが指定されていません");
        }
        
        int boardId = Integer.parseInt(boardIdStr);
        
        String deleteKey = request.getParameter("deleteKey");

        if (deleteKey != null && deleteKey.isEmpty()) {
            deleteKey = null; //nullでも削除
        }

        try {
            BoardDao dao = new BoardDao();
            dao.deleteBoard(boardId, deleteKey);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        response.sendRedirect("input");
    }
}
