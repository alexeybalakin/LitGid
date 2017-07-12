package ru.android.innocurses.litgid.models.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 08.07.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "litGid.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE 'users' ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                " `login` TEXT NOT NULL UNIQUE, `password` TEXT NOT NULL, `blocked` INTEGER NOT NULL DEFAULT 0 )");
        sqLiteDatabase.execSQL("CREATE TABLE `category` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`name` TEXT NOT NULL UNIQUE )");
        sqLiteDatabase.execSQL("CREATE TABLE 'writings' ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`name` TEXT NOT NULL, `text` TEXT NOT NULL, `category_id` INTEGER NOT NULL, `user_id` INTEGER NOT NULL," +
                " FOREIGN KEY(`category_id`) REFERENCES `category`(`id`), FOREIGN KEY(`user_id`) REFERENCES users(id) )");
        sqLiteDatabase.execSQL("CREATE TABLE `comments` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                "`comment` TEXT NOT NULL, `writing_id` INTEGER NOT NULL, `user_id` INTEGER NOT NULL," +
                " FOREIGN KEY(`writing_id`) REFERENCES writings(id), FOREIGN KEY(`user_id`) REFERENCES users(id) )");
        sqLiteDatabase.execSQL("INSERT INTO `users` VALUES (1,'admin','admin',0)");
        sqLiteDatabase.execSQL("INSERT INTO `category` VALUES (1,'Роман')");
        sqLiteDatabase.execSQL("INSERT INTO `writings` VALUES (1,'Вечерний звон','Много много текста',1,1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
