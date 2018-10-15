package com.example.arshu.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.res.Resources;


public class MainActivity extends AppCompatActivity {
    public int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        final String[] questions= res.getStringArray(R.array.questions);
        Button start = (Button)findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent ask = new Intent(MainActivity.this,AskQuestion.class);
                int resultCode =2;
                for(int i = 0; i < 5; i++){
                    ask.putExtra("Questions",questions[i]);
                    startActivityForResult(ask, resultCode);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==MainActivity.RESULT_OK) {
                count = count + Integer.parseInt(data.getStringExtra("MESSAGE"));
            }
        }

        Intent summary = new Intent(MainActivity.this, Summary.class);
        summary.putExtra("Score", count);
        startActivity(summary);
    }
}
