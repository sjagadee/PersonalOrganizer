package com.hello.android.srinivas.personalorganizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by srinivas on 2/12/17.
 *
 * This is where we are doing the important stuff
 *
 * 1. perform all crud operations here
 * 2. here we create all public methods, which would be exposed to other classes to use them
 *
 */

public class DatabaseAdapter {

    Context context;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    // helper method to open database
    public DatabaseAdapter openDatabase() {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    // helper method to close the database
    public void closeDatabase() {
        try {
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // helper method to insert the data to database
    public long addData(String item, String priority){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.ITEM_NAME, item);
            contentValues.put(Constants.PRIORITY_NAME, priority);

            return db.insert(Constants.TABLE_NAME, Constants.ROW_ID, contentValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //helper method to retrieve the data from database
    public Cursor getAllInformation() {
        String[] columns = {Constants.ROW_ID, Constants.ITEM_NAME, Constants.PRIORITY_NAME};

        return db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
    }

    // helper method to update
    public long updateData(int id, String item, String priority) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.ITEM_NAME, item);
            contentValues.put(Constants.PRIORITY_NAME, priority);

            // table name, content values, row id (in string)
            return db.update(Constants.TABLE_NAME, contentValues, Constants.ROW_ID + "=?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // helper method to delete
    public long deleteData(int id) {
        try {
            // table name, row id (in string)
            return db.delete(Constants.TABLE_NAME, Constants.ROW_ID + "=?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
