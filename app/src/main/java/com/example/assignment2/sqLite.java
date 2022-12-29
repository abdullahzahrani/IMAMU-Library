package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class sqLite extends SQLiteOpenHelper {

    public static final String DBName="data,db";

    public sqLite(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table mytable (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " author_name  TEXT, book_name TEXT,cat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String author_name, String book_name, String cat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author_name",author_name);
        contentValues.put("book_name",book_name);
        contentValues.put("cat",cat);
        long result = db.insert("mytable",null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }
    public ArrayList getAllRecord(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);

            arrayList.add(t1+ ",  "+t2+",  "+t3+",  "+t4);
            res.moveToNext();
        }
        return arrayList;
    }

    public boolean updateData(String id,String author_name, String book_name, String cat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author_name",author_name);
        contentValues.put("book_name",book_name);
        contentValues.put("cat",cat);
        db.update("mytable",contentValues,"id= ?",new String[]{id});
        return true;

    }
    public Integer Delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mytable","id = ?",new String[]{id});
    }
}