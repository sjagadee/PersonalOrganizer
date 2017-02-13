package com.hello.android.srinivas.personalorganizer;

/**
 * Created by srinivas on 2/12/17.
 * <p>
 * Here I am storing all the constants such as
 * 1. Database name
 * 2. Table name
 * 3. Database version
 * 4. Column name
 */

public class Constants {

    // columns
    static final String ROW_ID = "id";
    static final String ITEM_NAME = "item";
    static final String PRIORITY_NAME = "priority";

    // database properties
    static final String DATABASE_NAME = "to_do_DB";
    static final String TABLE_NAME = "to_do_TB";
    static final int DB_VERSION = 1;

    // create table
    static final String CREATE_TABLE = "CREATE TABLE to_do_TB(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "item TEXT NOT NULL, priority TEXT NOT NULL);";
}
