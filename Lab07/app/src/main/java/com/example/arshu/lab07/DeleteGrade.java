package com.example.arshu.lab07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class DeleteGrade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grade);

        ArrayList<String> ids = getIntent().getStringArrayListExtra("ids");

        Spinner spinner = (Spinner)findViewById(R.id.spnGrades);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void deleteGrade(View v) {
        Spinner spinner = (Spinner)findViewById(R.id.spnGrades);
        Intent intent  = new Intent();
        intent.putExtra("studentID", spinner.getSelectedItem().toString());

        setResult(ShowGrades.DELETE_GRADE_REQUEST, intent);
        finish();
    }
}
