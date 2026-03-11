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
import model.Reply;

public class BoardDao {

    // DB接続
    private Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
        return ds.getConnection();
    }

    // 投稿返信取得
    public List<Board> findAllWithReplies() throws Exception {
        List<Board> boards = new ArrayList<>();

        // 投稿取得
        String boardSql = "SELECT id, name, subject, message, email, created_at FROM boards ORDER BY created_at DESC";
        // 返信取得
        String replySql = "SELECT id, board_id, name, message, email, created_at FROM replies WHERE board_id = ? ORDER BY created_at ASC";

        try (Connection conn = getConnection();
             PreparedStatement psBoard = conn.prepareStatement(boardSql);
             PreparedStatement psReply = conn.prepareStatement(replySql)) {

            ResultSet rsBoard = psBoard.executeQuery();

            while (rsBoard.next()) {
                Board b = new Board();
                b.setId(rsBoard.getInt("id"));
                b.setName(rsBoard.getString("name"));
                b.setSubject(rsBoard.getString("subject"));
                b.setMessage(rsBoard.getString("message"));
                b.setEmail(rsBoard.getString("email"));
                b.setCreatedAt(rsBoard.getTimestamp("created_at"));

                // この投稿の返信を取得
                psReply.setInt(1, b.getId());
                ResultSet rsReply = psReply.executeQuery();

                List<Reply> replyList = new ArrayList<>();
                while (rsReply.next()) {
                    Reply r = new Reply();
                    r.setId(rsReply.getInt("id"));
                    r.setBoardId(rsReply.getInt("board_id"));
                    r.setName(rsReply.getString("name"));
                    r.setMessage(rsReply.getString("message"));
                    r.setEmail(rsReply.getString("email"));
                    r.setCreatedAt(rsReply.getTimestamp("created_at"));
                    replyList.add(r);
                }
                rsReply.close();

                b.setReplies(replyList);
                boards.add(b);
            }
            rsBoard.close();
        }

        return boards;
    }
}