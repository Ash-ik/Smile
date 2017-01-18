package com.askme.dreamhackathon.smile.DatabaseBuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBadapter {
    private Database database;
    private Context context;
    private SQLiteDatabase db;

    public DBadapter(Context context) {
        this.context = context;
        database = new Database(context);
        db = database.getWritableDatabase();
    }


    public void insertUser(String userName,String userEmail,String userPassword,String userContactNumber,String userType,String userPregnancyDate) {
        ContentValues values = new ContentValues();
        values.put(database.Column_NAME, userName);
        values.put(database.Column_EMAIL,userEmail);
        values.put(database.Column_PASSWORD,userPassword);
        values.put(database.Column_CONTACT_NUMBER,userContactNumber);
        values.put(database.Column_TYPE,userType);
        values.put(database.Column_PREGNANCY_DATE, userPregnancyDate);
        long insert = db.insert(database.loggedUserTableName, null, values);
    }

    public String[] getAllUser(){
        String[] DistinctName=null;
        String select_number_query="SELECT "+database.Column_NAME+" FROM "+database.loggedUserTableName;
        Cursor cursor = db.rawQuery(select_number_query, null);
        if (cursor != null && cursor.getCount() > 0) {
            int size = cursor.getCount();
            DistinctName=new String[size];
            cursor.moveToFirst();
            for (int i = 0; i < size; i++) {
                DistinctName[i] = cursor.getString(cursor.getColumnIndex(database.Column_NAME));
                cursor.moveToNext();
            }
        }
        return DistinctName;
    }

    public String[] getUser(String name)
    {
        String userInfo[]=new String[3];
        String select_date_query = "SELECT " +
                database.Column_NAME +","+database.Column_EMAIL +","+database.Column_PASSWORD+ " FROM " + database.loggedUserTableName+
                " WHERE "+database.Column_NAME+"="+"'"+name+"'";
        Cursor cursor = db.rawQuery(select_date_query, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            userInfo[0] = cursor.getString(cursor.getColumnIndex(database.Column_NAME));
            userInfo[1]=cursor.getString(cursor.getColumnIndex(database.Column_EMAIL));
            userInfo[2]=cursor.getString(cursor.getColumnIndex(database.Column_PASSWORD));
        }

        return userInfo;
    }
}
