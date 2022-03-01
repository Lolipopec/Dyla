package com.example.dyla;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "DylaDB";

    public static final String TABLE_USER= "user";
    public static final String ID_USER = "id_user";
    public static final String NAME_USER = "name_user";
    public static final String PASS_USER = "pass_user";
    public static final String MONEY_USER = "money_user";
    public static final String SCORE_USER = "score_user";
    public static final String DELETE_USER = "delete_user";



    public static final String TABLE_USER_ADD_APP= "user_add_app";
    public static final String ID_USER_ADD_APP = "id_user_app";


    public static final String TABLE_APP= "app";
    public static final String ID_APP = "id_app";
    public static final String APP_NAME = "app_name";
    public static final String APP_COST = "app_cost";
    public static final String APP_SCORE = "app_score";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER + "(" + ID_USER+ " integer primary key autoincrement not null," + NAME_USER + " text NOT NULL," + PASS_USER  + " text NOT NULL,"+MONEY_USER+" integer NOT NULL DEFAULT 0, "+SCORE_USER+" integer NOT NULL DEFAULT 0, " + DELETE_USER + " integer NOT NULL DEFAULT 0 "+")");
        db.execSQL("create table " + TABLE_APP + "(" +ID_APP + " integer primary key autoincrement not null," + APP_NAME + " text NOT NULL," + APP_COST + " integer NOT NULL," + APP_SCORE + " integer NOT NULL"+")");
        db.execSQL("create table " + TABLE_USER_ADD_APP + "(" +ID_USER_ADD_APP + " integer primary key autoincrement not null," + ID_USER + " integer NOT NULL," + ID_APP + " integer NOT NULL,"
                + "FOREIGN KEY (" +ID_USER+ ")"+"REFERENCES "+TABLE_USER+"("+ID_USER+"),"
                + "FOREIGN KEY (" +ID_APP+ ")"+"REFERENCES "+TABLE_APP+"("+ID_APP+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USER);
        db.execSQL("drop table if exists " + TABLE_APP);
        db.execSQL("drop table if exists " + TABLE_USER_ADD_APP);

        onCreate(db);

    }
}