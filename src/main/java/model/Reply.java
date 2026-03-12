package model;

import java.sql.Timestamp;

public class Reply {

    private int id;
    private int boardId;
    private String name;
    private String message;
    private Timestamp createdAt;

    // --- getter / setter ---
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}