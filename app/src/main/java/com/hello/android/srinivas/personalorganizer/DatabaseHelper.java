package com.hello.android.srinivas.personalorganizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by srinivas on 2/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DB_VERSION);
    }

    // gets called when created
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            // create table here from constants class
            db.execSQL(Constants.CREATE_TABLE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // gets called when the table  is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }
}
