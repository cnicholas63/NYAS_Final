package com.example.chris.nyas_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chris on 19/05/2015.
 * Database helper class, provides methods for accessing the appointments database
 * Adapted from http://www.vogella.com/tutorials/AndroidSQLite/article.html
 */
public class DatabaseHelper extends SQLiteOpenHelper implements AppConstants {


    private static final String DATABASE_CREATE = // The SQLite string that used to create
        "create table " + DATABASE_NAME + TABLE_ENTRIES + " (" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_TYPE + " integer, " + COLUMN_DATE + " text, " +
            COLUMN_START_TIME + " text, " + COLUMN_END_TIME + " text, " +
            COLUMN_TITLE + " text, " + COLUMN_CONTENT + "text);";

    // Constructor for the class
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // Call super constructor with database info

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE); // Execute the DATABASE_CREATE sql string if database does not exist
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // not needed in this app - at this stage
    }
}
