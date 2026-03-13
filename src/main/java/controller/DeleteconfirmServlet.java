package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteconfirmServlet")
public class DeleteconfirmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boardIdStr = request.getParameter("boardId");

        System.out.println("DeleteconfirmServlet boardId = " + boardIdStr);

        if (boardIdStr == null || boardIdStr.isEmpty()) {
            response.getWriter().println("boardId が来ていません");
            return;
        }

        int boardId = Integer.parseInt(boardIdStr);

        request.setAttribute("boardId", boardId);
        request.getRequestDispatcher("/WEB-INF/jsp/Delete.jsp")
               .forward(request, response);
    }
}
	
