package ru.android.innocurses.litgid.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ru.android.innocurses.litgid.models.SQLite.DBHelper;
import ru.android.innocurses.litgid.models.User;

/**
 * Created by admin on 08.07.2017.
 */

public class ManagerUsers {
    private Context context;
    private static ManagerUsers managerUsers;
    private static SQLiteDatabase database;

    public static ManagerUsers get(Context context){
        if(managerUsers == null){
            managerUsers = new ManagerUsers(context);
        }
        return managerUsers;
    }
    private ManagerUsers(Context context){
        this.context = context.getApplicationContext();
        database = new DBHelper(this.context).getWritableDatabase();
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put("login", user.getLogin());
        values.put("password", user.getPassword());
        return values;
    }

    //Добавление в БД нового пользователя
    public  void addUser(User user){
        ContentValues values = getContentValues(user);
        database.insert("users", null, values);

    }

    //Возвращаем из БД пользователь с указанным логином
    public User getUser(String login){
        Cursor cursor = query("login = ?", new String[]{login});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return new User(cursor.getString(cursor.getColumnIndex("login")), cursor.getString(cursor.getColumnIndex("password")));
        }
        finally {
            cursor.close();
        }
    }

    private Cursor query(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                "litGid.db",
                null, // Columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return cursor;
    }
}
