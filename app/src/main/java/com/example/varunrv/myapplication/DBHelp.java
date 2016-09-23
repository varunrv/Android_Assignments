package com.example.varunrv.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vvadiraj on 9/21/2016.
 */
public class DBHelp extends SQLiteOpenHelper {

    private static final String databaseName = "location.db";
    private static final String tableName = "Location_Details";
    private static final String tableName2 = "Location_Info";

    private static final String address = "_adresss";
    private static final String id = "_ID";
    private static final String lat = "Latitude";
    private static final String lon = "Longitude";

    private  static final String createQ = "CREATE TABLE " + tableName + "(" + address + " VARCHAR(25), " + lat + " NUMBER, " + lon + " NUMBER);";
    //static final String createQ2 = "CREATE TABLE " + tableName + "( " + id + "INTEGER PRIMARY KEY AUTOINCREMENT, " + address + " VARCHAR(25), " + lat + " NUMBER, " + lon + " NUMBER);";

    static final String delete = "DROP TABLE IF EXISTS" + tableName;
    private static final int DATABASE_VERSION = 33;

    DBHelp(Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insert(String address, String lat, String lon) {  //function to insert values into table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(this.address, address);
        c.put(this.lat, lat);
        c.put(this.lon, lon);
        long id = db.insert(this.tableName, null, c);
        return id;
    }

    public Cursor getdata() {                           //get data from table
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + tableName, null);
        return res;

    }


}
