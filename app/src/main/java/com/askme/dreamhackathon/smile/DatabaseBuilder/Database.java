package com.askme.dreamhackathon.smile.DatabaseBuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    //  table name
    public static final String loggedUserTableName = "loggedUserTable";
    //table columnsDatabase_Name
    public static final String Column_ID = "_id";
    public static final String Column_NAME = "name";
    public static final String Column_EMAIL = "email";
    public static final String Column_PASSWORD = "password";
    public static final String Column_CONTACT_NUMBER = "contact_number";
    public static final String Column_TYPE = "type";
    public static final String Column_PREGNANCY_DATE = "pregnancy_date";





    //Database Create Query
    private static final String CREATE_QUERY = "CREATE TABLE " + loggedUserTableName + " (" +
            Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_NAME + " TEXT, " +
            Column_EMAIL + " TEXT, " +
            Column_PASSWORD + " TEXT, " +
            Column_CONTACT_NUMBER + " TEXT, " +
            Column_TYPE + " TEXT, " +
            Column_PREGNANCY_DATE + " TEXT);";


    public Database(Context context) {
        super(context, "com.askme.dreamhackathon.localDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Created","Created");
        db.execSQL(CREATE_QUERY); //Create table FileTable
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Starting the database


}

