package com.example.arshu.lab03;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button about = (Button)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Login.class);
                int resultCode =2;
                startActivityForResult(intent, resultCode);
            }
        });

    }
    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==MainActivity.RESULT_OK) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence message = (CharSequence) data.getStringExtra("MESSAGE");
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        }
    }
}
