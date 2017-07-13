package ru.android.innocurses.litgid.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.android.innocurses.litgid.models.Category;
import ru.android.innocurses.litgid.models.SQLite.DBHelper;
import ru.android.innocurses.litgid.models.User;
import ru.android.innocurses.litgid.models.Writing;

/**
 * Created by admin on 12.07.2017.
 */

public class ManagerWritings {
    private Context context;
    private static ManagerWritings managerWritings;
    private static SQLiteDatabase database;

    public static ManagerWritings get(Context context){
        if(managerWritings == null){
            managerWritings = new ManagerWritings(context);
        }
        return managerWritings;
    }
    private ManagerWritings(Context context){
        this.context = context.getApplicationContext();
        database = new DBHelper(this.context).getWritableDatabase();
    }

    private static ContentValues getContentValues(Writing writing) {
        ContentValues values = new ContentValues();
        values.put("name", writing.getName());
        values.put("text", writing.getText());
        values.put("category_id", writing.getCategory().getId());
        values.put("user_id", writing.getAuthor().getId());
        return values;
    }

    //Добавляем в БД новое лит. произведение
    public  void addWriting(Writing writing){
        ContentValues values = getContentValues(writing);
        database.insert("writings", null, values);
    }

    //Удаляем из БД  лит. произведение
    public  void delWriting(Writing writing){
        database.delete("writings","id = ?", new String[]{""+writing.getId()});
    }

    private Cursor query(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                "writings",
                null, // Columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return cursor;
    }


    //Получаем из БД список лит. произведений
    public List<Writing> getWritings() {

        List<Writing> writings = new ArrayList<>();
        Cursor cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String name  = cursor.getString(cursor.getColumnIndex("name"));
                String text = cursor.getString(cursor.getColumnIndex("text"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Category category = ManagerCategories.get(context).getCategory(cursor.getInt(cursor.getColumnIndex("category_id")));
                User user = ManagerUsers.get(context).getUser(cursor.getInt(cursor.getColumnIndex("user_id")));
                Writing writing = new Writing(id, name,text, category, user);
                writings.add(writing);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return writings;
}

    //Получаем из БД лит. произведение с указанным id
    public Writing getWriting(int writingId){
        Cursor cursor = query("id = ?", new String[]{""+writingId});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            String name  = cursor.getString(cursor.getColumnIndex("name"));
            String text = cursor.getString(cursor.getColumnIndex("text"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Category category = ManagerCategories.get(context).getCategory(cursor.getInt(cursor.getColumnIndex("category_id")));
            User user = ManagerUsers.get(context).getUser(cursor.getInt(cursor.getColumnIndex("user_id")));
            Writing writing = new Writing(id, name,text, category, user);
            return writing;
        }
        finally {
            cursor.close();
        }
    }
}
