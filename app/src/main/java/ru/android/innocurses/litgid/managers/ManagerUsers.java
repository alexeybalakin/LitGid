package ru.android.innocurses.litgid.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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
        values.put("blocked", user.isBlocked() ? 1 : 0);
        return values;
    }

    //Добавляем в БД нового пользователя
    public  void addUser(User user){
        ContentValues values = getContentValues(user);
        database.insert("users", null, values);

    }

    //Изменяем информацию о пользователе в БД
    public void updateUser(User user){
        ContentValues values = getContentValues(user);
        database.update("users", values, "id = ?", new String[]{""+user.getId()});
    }

    //Получаем из БД пользователя с указанным логином
    public User getUser(String userLogin){
        Cursor cursor = query("login = ?", new String[]{userLogin});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            String login = cursor.getString(cursor.getColumnIndex("login"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int blocked =  cursor.getInt(cursor.getColumnIndex("blocked"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            User user = new User(login,password, id);
            if(blocked == 1) user.setBlocked(true);
            return user;
        }
        finally {
            cursor.close();
        }
    }

    //Получаем из БД пользователя с указанным id
    public User getUser(int loginId){
        Cursor cursor = query("id = ?", new String[]{""+loginId});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            String login = cursor.getString(cursor.getColumnIndex("login"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int blocked =  cursor.getInt(cursor.getColumnIndex("blocked"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            User user = new User(login,password, id);
            if(blocked == 1) user.setBlocked(true);
            return user;
        }
        finally {
            cursor.close();
        }
    }

    private Cursor query(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                "users",
                null, // Columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return cursor;
    }


    //Получаем из БД всех пользователей
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();
        Cursor cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String login = cursor.getString(cursor.getColumnIndex("login"));
                String password = cursor.getString(cursor.getColumnIndex("password"));
                int blocked =  cursor.getInt(cursor.getColumnIndex("blocked"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                User user = new User(login,password, id);
                if(blocked == 1) user.setBlocked(true);
                users.add(user);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    //Удаляем из БД пользователя
    public  void delCategory(User user){
        database.delete("users","id = ?", new String[]{""+user.getId()});
    }

}
