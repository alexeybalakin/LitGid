package ru.android.innocurses.litgid.models;

/**
 * Created by admin on 08.07.2017.
 */

public class User {
   private int id;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

