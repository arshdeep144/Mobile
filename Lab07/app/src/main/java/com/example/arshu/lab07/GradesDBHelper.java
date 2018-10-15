package com.example.arshu.lab07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arshu on 2017-11-14.
 */

public class GradesDBHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "grades1";

    static final String CREATE_STATEMENT = "CREATE TABLE grades1 (\n" +
            "      studentID integer primary key autoincrement,\n" +
            "      courseComponent varchar(100) not null,\n" +
            "      mark decimal not null);\n";

    static final String DROP_STATEMENT = "DROP TABLE grades1";

    public GradesDBHelper(Context context){super(context, TABLE, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum) {
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);
    }

    public Grades createGrades(String courseComponent, float mark){
        Grades grade = new Grades(courseComponent, mark);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("courseComponent", courseComponent);
        newValues.put("mark", mark);
        int studentID = (int) db.insert(TABLE, null, newValues);
        grade.setStudentID(studentID);

        return grade;
    }

//    public Grades getGrades(int studentID){
//        Grades grades = null;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns = new String[] {"studentID", "courseComponent", "mark"};
//        String where = "studentID = ?";
//        String[] whereArgs = new String[] {"" + studentID};
//        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");
//
//        if(cursor.getCount() >= 1){
//            cursor.moveToFirst();
//
//            String courseComponent = cursor.getString(1);
//            float mark = cursor.getFloat(2);
//
//            grades = new Grades(courseComponent, mark);
//            grades.setStudentID(studentID);
//        }
//        return grades;
//    }
    public List<Grades> getAllContacts(){
        List<Grades> grades = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"studentID", "courseComponent", "mark"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "courseComponent");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                int studentID = cursor.getInt(0);
                String courseComponent = cursor.getString(1);
                Float mark = cursor.getFloat(2);

                Grades grade = new Grades(courseComponent, mark);
                grade.setStudentID(studentID);

                grades.add(grade);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());;

        return grades;
    }

//    public boolean updateContact(Grades grade) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("courseComponent", grade.getCourseComponent());
//        values.put("mark", grade.getMark());
//
//        int numRows = db.update(TABLE, values, "studentID = ?", new String[] { "" + grade.getStudentID() });
//
//        return (numRows == 1);
//    }

    // DELETE
    public boolean deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "studentID = ?", new String[] { "" + id });

        return (numRows == 1);
    }


}
