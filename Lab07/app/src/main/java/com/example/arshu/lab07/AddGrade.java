package com.example.arshu.lab07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddGrade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
    }

    public void addGrade(View v) {
        // Find views
        TextView txtGradeCourseComponent = (TextView)findViewById(R.id.txtGradeCourseComponent);
        TextView txtGradeMark = (TextView)findViewById(R.id.txtGradeMark);

        Intent intent  = new Intent();
        intent.putExtra("courseComponent", txtGradeCourseComponent.getText().toString());
        intent.putExtra("mark", Float.parseFloat(txtGradeMark.getText().toString()));

        setResult(ShowGrades.ADD_GRADE_REQUEST, intent);
        finish();
    }
}
