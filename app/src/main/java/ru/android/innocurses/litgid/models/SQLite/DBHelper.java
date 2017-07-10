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

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
