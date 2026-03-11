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

@WebServlet("/input")
public class InputServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            BoardDao dao = new BoardDao();

            // 投稿＋返信を取得
            List<Board> list = dao.findAllWithReplies();

            // JSP に渡す
            request.setAttribute("boards", list);

            // フォワード
            request.getRequestDispatcher("/WEB-INF/jsp/input.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}