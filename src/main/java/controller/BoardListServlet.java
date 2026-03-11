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

@WebServlet("/boards")  // URL
public class BoardListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // DAO作成
            BoardDao dao = new BoardDao();

            // 投稿返信取得
            List<Board> boards = dao.findAllWithReplies();

            // JSPに渡す
            request.setAttribute("boards", boards);

            // フォワード
            request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("掲示板一覧取得エラー", e);
        }
    }
}