package com.example.arshu.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        int score = getIntent().getIntExtra("Score", 0);
        TextView summary = (TextView) findViewById(R.id.summary);
        String result = "You answered " + Integer.toString(score) + " yes and " +
                Integer.toString(5 - score) + " no.";
        summary.setText(result);
    }
}
