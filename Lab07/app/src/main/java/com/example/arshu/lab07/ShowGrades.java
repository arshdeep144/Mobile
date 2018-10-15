package com.example.arshu.lab07;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowGrades extends AppCompatActivity {
    public static final int ADD_GRADE_REQUEST = 10000;
    public static final int DELETE_GRADE_REQUEST = 10001;
    private List<Grades> grades;
    private ArrayAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grades);
        GradesDBHelper gradesDBHelper = new GradesDBHelper(this);
        grades = gradesDBHelper.getAllContacts();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, grades);
        listView = (ListView) findViewById(R.id.lstGrades);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        GradesDBHelper gradesDBHelper = new GradesDBHelper(this);
        if (requestCode == ADD_GRADE_REQUEST) {
            // Read contact
            String courseComponent = data.getStringExtra("courseComponent");
            Float mark = data.getFloatExtra("mark", -1);
            grades.add(gradesDBHelper.createGrades(courseComponent, mark));
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == DELETE_GRADE_REQUEST){
            int studentID = Integer.parseInt(data.getStringExtra("studentID"));
            gradesDBHelper.deleteContact(studentID);
            for (int i = 0; i < grades.size(); i++) {
                Grades c = grades.get(i);
                if (c.getStudentID()==studentID) {
                    grades.remove(i);
                    adapter.notifyDataSetChanged();
                    break;
                }
            }

        }
//        grades = gradesDBHelper.getAllContacts();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addGrade(View v) {
        Intent intent = new Intent(this, AddGrade.class);
        startActivityForResult(intent, ADD_GRADE_REQUEST);
    }

    public void deleteGrade(View v) {
        Intent intent = new Intent(this, DeleteGrade.class);
        ArrayList<String> ids = new ArrayList<>();
        for (Grades c: grades) {
            ids.add(Integer.toString(c.getStudentID()));
        }
        intent.putStringArrayListExtra("ids", ids);
        startActivityForResult(intent, DELETE_GRADE_REQUEST);
    }

}
