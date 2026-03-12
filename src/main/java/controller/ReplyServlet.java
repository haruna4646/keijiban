package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReplyDao;
import model.Reply;

@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int boardId = Integer.parseInt(request.getParameter("boardId"));
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        try {
            Reply reply = new Reply();
            reply.setBoardId(boardId);
            reply.setName(name);
            reply.setMessage(message);

            ReplyDao dao = new ReplyDao();
            dao.insert(reply);

        } catch (Exception e) {
            throw new ServletException(e);
        }

        // input画面に戻す
        response.sendRedirect("input");
    }
}