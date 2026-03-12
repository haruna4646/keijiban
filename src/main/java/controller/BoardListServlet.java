package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import model.Board;

@WebServlet("/boards")
public class BoardListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            BoardDao dao = new BoardDao();

            // 投稿＋返信をまとめて取得
            List<Board> boards = dao.findAllWithReplies();

            request.setAttribute("boards", boards);
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("掲示板一覧取得エラー", e);
        }
    }
}