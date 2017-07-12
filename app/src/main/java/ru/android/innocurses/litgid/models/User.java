package ru.android.innocurses.litgid.models;

/**
 * Created by admin on 08.07.2017.
 */

public class User {
   private int id;
    private String login;
    private String password;
    private boolean blocked;
    private boolean admin;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.blocked = false;
        this.admin = false;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }
}

