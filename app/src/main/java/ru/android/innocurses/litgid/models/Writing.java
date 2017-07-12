package ru.android.innocurses.litgid.models;

/**
 * Created by admin on 12.07.2017.
 */

public class Writing {
   private int id;
   private String name;
   private String text;
   private Category category;
    private User author;


    public int getId() {
        return id;
    }

    public Writing(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Writing(int id, String name, String text, Category category, User author) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.category = category;
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
