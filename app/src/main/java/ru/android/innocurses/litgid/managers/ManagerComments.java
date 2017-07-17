package ru.android.innocurses.litgid.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import ru.android.innocurses.litgid.models.Comment;
import ru.android.innocurses.litgid.models.SQLite.DBHelper;
import ru.android.innocurses.litgid.models.User;
import ru.android.innocurses.litgid.models.Writing;

/**
 * Created by admin on 13.07.2017.
 */

public class ManagerComments {
    private Context context;
    private static ManagerComments managerComments;
    private static SQLiteDatabase database;

    public static ManagerComments get(Context context){
        if(managerComments == null){
            managerComments = new ManagerComments(context);
        }
        return managerComments;
    }
    private ManagerComments(Context context){
        this.context = context.getApplicationContext();
        database = new DBHelper(this.context).getWritableDatabase();
    }

    private static ContentValues getContentValues(Comment comment) {
        ContentValues values = new ContentValues();
        values.put("comment", comment.getComment());
        values.put("writing_id", comment.getWriting().getId());
        values.put("user_id", comment.getUser().getId());
        return values;
    }

    //Добавляем в БД новый комментарий
    public  void addComment(Comment comment){
        ContentValues values = getContentValues(comment);
        database.insert("comments", null, values);
    }

    //Удаляем из БД  комментарий
    public  void delComment(Comment comment){
        database.delete("comments","id = ?", new String[]{""+comment.getId()});
    }

    //Удаляем из БД  все комментарии относящиеся к данному лит. произведению
    public  void delComments(Writing writing){
        database.delete("comments","writing_id = ?", new String[]{""+writing.getId()});
    }

    private Cursor query(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                "comments",
                null, // Columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return cursor;
    }


    //Получаем из БД список всех комментариев
    public List<Comment> getComments() {

        List<Comment> comments = new ArrayList<>();
        Cursor cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String commentName  = cursor.getString(cursor.getColumnIndex("comment"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Writing writing = ManagerWritings.get(context).getWriting(cursor.getInt(cursor.getColumnIndex("writing_id")));
                User user = ManagerUsers.get(context).getUser(cursor.getInt(cursor.getColumnIndex("user_id")));
                Comment comment = new Comment(id, commentName, writing, user);
                comments.add(comment);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return comments;
    }

    //Получаем из БД список комментариев соответсвующих id Writing
    public List<Comment> getComments(int writingId) {

        List<Comment> comments = new ArrayList<>();
        Cursor cursor = query("writing_id = ?", new String[]{""+writingId});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String commentName  = cursor.getString(cursor.getColumnIndex("comment"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Writing writing = ManagerWritings.get(context).getWriting(cursor.getInt(cursor.getColumnIndex("writing_id")));
                User user = ManagerUsers.get(context).getUser(cursor.getInt(cursor.getColumnIndex("user_id")));
                Comment comment = new Comment(id, commentName, writing, user);
                comments.add(comment);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return comments;
    }
}

