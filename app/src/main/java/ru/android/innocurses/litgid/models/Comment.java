package ru.android.innocurses.litgid.models;

/**
 * Created by admin on 12.07.2017.
 */

public class Comment {
    private int id;
    private String comment;
    private Writing writing;
    private User user;

    public Comment(String comment, Writing writing, User user) {
        this.comment = comment;
        this.writing = writing;
        this.user = user;
    }

    public Comment(int id, String comment, Writing writing, User user) {
        this.id = id;
        this.comment = comment;
        this.writing = writing;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Writing getWriting() {
        return writing;
    }

    public void setWriting(Writing writing) {
        this.writing = writing;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
