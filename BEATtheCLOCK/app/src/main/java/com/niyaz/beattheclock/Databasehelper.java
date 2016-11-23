package com.niyaz.beattheclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;
import android.database.Cursor;
import android.os.SystemClock;

import java.util.jar.Attributes;

/**
 * Created by niyaz on 2016-10-12.
 */

public class Databasehelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "beaththeclock.db";
    public static final String TABLE_NAME = "quiztakers";
    public static final String IDQ = "ID_Q";
    public static final String POINTS = "POINTS";
    public static final String NAME = "UNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String PLAYED = "PLAYED";
    public static final String TABLE_NAME1 = "questions";
    public static final String ID = "ID";
    public static final String QUESTION = "QUESTION";
    public static final String ANSWER = "ANSWER";
    public static final String OPTION1 = "OPTION1";
    public static final String OPTION2 = "OPTION2";
    public static final String OPTION3 = "OPTION3";
    public static final String TIME = "TIME";
    public static final String TABLE_NAME3 = "STATS";
    private static String QS_ID = "sID";
    private static String QS_Username = "sUNAME";
    private static String QS_Round = "ROUND";
    private static String QS_QuizScore = "SCORE";

    public final String DATABASE_CREATE_TABLE1 = "create table quiztakers(ID_Q integer primary key autoincrement,POINTS text, UNAME text not null," +
            " PASSWORD text);";
    public final String DATABASE_CREATE_TABLE2 = "create table questions(ID integer primary key autoincrement," +
            "QUESTION text not null, ANSWER text, OPTION1 text, OPTION2 text, OPTION3 text, TIME integer);";
    public final String DATABASE_CREATE_TABLE3 = "CREATE TABLE STATS( sID INTEGER NOT NULL,sUNAME TEXT NOT NULL,ROUND INTEGER NOT NULL,SCORE" +
            " INTEGER NOT NULL,FOREIGN KEY (sID) REFERENCES quiztakers(ID_Q),"
            + "FOREIGN KEY (sUNAME) REFERENCES quiztakers(UNAME),"
            + "PRIMARY KEY (sID,ROUND))";


    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE_TABLE1);
            db.execSQL(DATABASE_CREATE_TABLE2);
            db.execSQL(DATABASE_CREATE_TABLE3);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertdata(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("POINTS","0");
        contentValues.put("UNAME",name);
        contentValues.put("PASSWORD",password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }
    public boolean insertstat(String id, String name, String round, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sID",id);
        contentValues.put("sUNAME",name);
        contentValues.put("ROUND",round);
        contentValues.put("SCORE",score);
        long result = db.insert(TABLE_NAME3,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }

    public boolean insertques(String question, String answer,String option1, String option2, String option3, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("QUESTION",question);
        contentValues.put("ANSWER",answer);
        contentValues.put("OPTION1",option1);
        contentValues.put("OPTION2",option2);
        contentValues.put("OPTION3",option3);
        contentValues.put("TIME",time);
        long result = db.insert(TABLE_NAME1,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }


    public Cursor getalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from quiztakers",null);
        return res;
    }
    public Cursor getallques(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from questions",null);
        return res;
    }
    public Cursor getallstat(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from STATS",null);
        return res;
    }


    public boolean CheckData(String fieldValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from quiztakers where UNAME = " + fieldValue;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public Integer delstat(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID_Q = ?",new String[] {id});


    }

    public void update(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("POINTS","100");
        db.update(TABLE_NAME,contentValues,"ID_Q = ?",new String[] {id});

    }

    public void updatepoint(String name,String str) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("POINTS",str);
        db.update(TABLE_NAME,contentValues,"UNAME = ?",new String[] {name});

    }

    public String getpassword(String user){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =db.query("quiztakers",null,"UNAME=?",new  String[] {user},null,null,null );
        if (cursor.getCount()<1){
            cursor.close();
            return "USER DOES NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;

    }

    public  Cursor getquesbyid(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("questions",null,"ID=?",new  String[] {id},null,null,null );
        cursor.moveToFirst();
        return cursor;
    }
    public  Cursor getstatbyname(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "SELECT * FROM " + TABLE_NAME3 + " WHERE sUNAME = ?";
        Cursor cursor = db.rawQuery(str,new  String[] {name});
      //  cursor.moveToFirst();
        return cursor;
    }

    public String getdatabyname(String usname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("quiztakers",null,"UNAME=?",new  String[] {usname},null,null,null );
        cursor.moveToFirst();
        String id= cursor.getString(cursor.getColumnIndex("ID_Q"));
        cursor.close();
        return id;
    }

    public Cursor getround(String id){
        SQLiteDatabase db = this.getWritableDatabase();
       String str= " select * from STATS where sID=?";

        Cursor cursor = db.rawQuery(str,new String[]{id});
        System.out.println(cursor.getCount());
        cursor.moveToFirst();
        return cursor;

    }
    public int clearqtstat(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME3, "sID = ? ", new String[] { id });
    }
}
