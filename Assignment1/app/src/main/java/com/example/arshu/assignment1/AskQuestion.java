package com.example.arshu.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AskQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        String questions = getIntent().getStringExtra("Questions");
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(questions);
        Button yes = (Button)findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","1");
                setResult(MainActivity.RESULT_OK,intent);
                finish();
            }
        });
        Button no = (Button)findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("MESSAGE","0");
                setResult(MainActivity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
