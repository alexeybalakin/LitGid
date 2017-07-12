package ru.android.innocurses.litgid.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.android.innocurses.litgid.models.Category;
import ru.android.innocurses.litgid.models.SQLite.DBHelper;


/**
 * Created by admin on 10.07.2017.
 */

public class ManagerCategories {
    private Context context;
    private static ManagerCategories managerCategories;
    private static SQLiteDatabase database;

    public static ManagerCategories get(Context context){
        if(managerCategories == null){
            managerCategories = new ManagerCategories(context);
        }
        return managerCategories;
    }
    private ManagerCategories(Context context){
        this.context = context.getApplicationContext();
        database = new DBHelper(this.context).getWritableDatabase();
    }

    private static ContentValues getContentValues(Category category) {
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        return values;
    }

    //Добавляем в БД новоую категорию лит. произведения
    public  void addCategory(Category category){
        ContentValues values = getContentValues(category);
        database.insert("category", null, values);
    }

    //Удаляем из БД категорию лит. произведения
    public  void delCategory(Category category){
        database.delete("category","id = ?", new String[]{""+category.getId()});
    }

    private Cursor query(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                "category",
                null, // Columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return cursor;
    }


    //Получаем из БД виды лит. произведений
    public List<Category> getCategories() {

        List<Category> categories = new ArrayList<>();
        Cursor cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Category category = new Category(id, name);
                categories.add(category);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return categories;
    }
}


