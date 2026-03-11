package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Board;

public class BoardDao {

    // DB接続（JNDI）
    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds =
            (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
        return ds.getConnection();
    }

    // 投稿一覧取得（新しい順）
    public List<Board> findAllOrderByNew() throws Exception {

        List<Board> list = new ArrayList<>();

        String sql =
            "SELECT id, name, subject, message, email, created_at " +
            "FROM boards " +
            "ORDER BY created_at DESC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Board b = new Board();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setSubject(rs.getString("subject"));
                b.setMessage(rs.getString("message"));
                b.setEmail(rs.getString("email"));
                b.setCreatedAt(rs.getTimestamp("created_at"));

                list.add(b);
            }
        }

        return list;
    }

    // 投稿登録（INSERT）
    public void insert(Board board) throws Exception {

        String sql =
            "INSERT INTO boards (name, subject, message, email) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, board.getName());
            ps.setString(2, board.getSubject());
            ps.setString(3, board.getMessage());
            ps.setString(4, board.getEmail());

            ps.executeUpdate();
        }
    }
}