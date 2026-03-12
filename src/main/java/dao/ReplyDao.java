package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Reply;

public class ReplyDao {

    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
        return ds.getConnection();
    }

    // 返信を保存
    public void insert(Reply reply) throws Exception {
        String sql ="INSERT INTO replies (board_id, name, message) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reply.getBoardId());
            stmt.setString(2, reply.getName());
            stmt.setString(3, reply.getMessage());
            stmt.executeUpdate();
        }
    }

    // 返信取得
    public List<Reply> findByBoardId(int boardId) throws Exception {
        List<Reply> replies = new ArrayList<>();

        String sql ="SELECT * FROM replies WHERE board_id = ? AND is_deleted = FALSE ORDER BY created_at ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, boardId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reply r = new Reply();
                r.setId(rs.getInt("id"));
                r.setBoardId(rs.getInt("board_id"));
                r.setName(rs.getString("name"));
                r.setMessage(rs.getString("message"));
                r.setCreatedAt(rs.getTimestamp("created_at"));
                replies.add(r);
            }
        }
        return replies;
    }
}