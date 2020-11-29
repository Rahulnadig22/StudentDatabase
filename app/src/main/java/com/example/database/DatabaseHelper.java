package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "student_info";
    private static final String col_id = "id";
    private static final String col_stu_name = "stu_name";
    private static final String col_stu_roll = "stu_roll";
    private static final String col_bg = "stu_bg";
    private static final String col_dob = "stu_dob";

    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+"("+col_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ col_stu_name+" TEXT,"+col_stu_roll+" INTEGER,"+col_bg+" TEXT,"+col_dob+" TEXT)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "studentinfo.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDataToDatabase(SQLiteDatabase db,student st){
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_stu_name, st.getName());
        contentValues.put(col_stu_roll,st.getRollNo());
        contentValues.put(col_bg,st.getBG());
        contentValues.put(col_dob,st.getDOB());

        long value = db.insert(TABLE_NAME,null,contentValues);
        Log.i("DATABASE INSERT VALUE",String.valueOf(value));

    }

    public ArrayList<student> getStudentFromDatabase(SQLiteDatabase db){
        ArrayList<student> studentList = new ArrayList<student>();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        if (cursor.moveToFirst()){
            do {
                student studentInfo = new student();
                studentInfo.setName(cursor.getString(cursor.getColumnIndex(col_stu_name)));
                studentInfo.setRollNo((long) cursor.getInt(cursor.getColumnIndex(col_stu_roll)));
                studentInfo.setBG(cursor.getString(cursor.getColumnIndex(col_bg)));
                studentInfo.setDOB(cursor.getString(cursor.getColumnIndex(col_dob)));
                studentList.add(studentInfo);
            }while (cursor.moveToNext());
        }
        return studentList;
    }

    public void deleteStudent(student student,SQLiteDatabase database){
        database.delete(TABLE_NAME, col_id+"="+student.getId(),null);
    }

    public void updateStudent(student student,SQLiteDatabase database){
        ContentValues cv = new ContentValues();
        cv.put(col_stu_name, student.getName());
        cv.put(col_stu_roll,student.getRollNo());
        cv.put(col_bg,student.getBG());
        cv.put(col_dob,student.getDOB());

        database.update(TABLE_NAME,cv,col_id+"="+student.getId(),null);
    }
}
