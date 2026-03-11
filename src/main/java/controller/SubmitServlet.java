package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitServlet")
public class SubmitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //値を取得
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // DB接続して保存
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // JDBCドライバ読み込み
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB接続
            String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "Haruna4646";
            conn = DriverManager.getConnection(url, user, password);

            // SQL作成
            String sql = "INSERT INTO boards(name, email, subject, message, created_at) VALUES(?, ?, ?, ?, NOW())";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, title);
            ps.setString(4, content);

            ps.executeUpdate(); // 保存

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if(ps != null) ps.close(); } catch (SQLException e) {}
            try { if(conn != null) conn.close(); } catch (SQLException e) {}
        }

        response.sendRedirect("input");
    }
}
