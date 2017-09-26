package com.motivaimagine.motivaimagine_trial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gpaez on 9/25/2017.
 */

public class DB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_TOKEN = "token";
    public static final String USER_COLUMN_TYPE = "type";
    public static final String USER_COLUMN_TYPE_ID = "type_id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_LAST_NAME = "lastname";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PICTURE = "picture";

    private HashMap hp;

    public DB(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table user " +
                        "(id integer primary key, token text, type integer, type integer ,name text, lastname text, email text, picture text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertUser (int id,String token, int type , int type_id, String name, String lastname, String email, String picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("token", token);
        contentValues.put("type", type);
        contentValues.put("type_id", type_id);
        contentValues.put("name", name);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("picture", picture);
        db.insert("user", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser (int id,String token, int type , int type_id, String name, String lastname, String email, String picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("token", token);
        contentValues.put("type", type);
        contentValues.put("type_id", type_id);
        contentValues.put("name", name);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("picture", picture);
        db.update("user", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteUser (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getUser() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
