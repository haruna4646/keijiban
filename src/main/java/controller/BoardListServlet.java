package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>掲示板一覧</h2>");

        try {
            BoardDao dao = new BoardDao();
            List<Board> list = dao.findAllOrderByNew();

            for (Board b : list) {
                out.println("<hr>");
                out.println("件名：" + b.getSubject() + "<br>");
                out.println("名前：" + b.getName() + "<br>");
                out.println("本文：" + b.getMessage() + "<br>");
            }

        } catch (Exception e) {
            out.println("エラーが発生しました");
            e.printStackTrace(out);
        }
    }
}