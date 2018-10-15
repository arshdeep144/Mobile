package com.example.arshu.lab03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText password= (EditText)findViewById(R.id.password_text);
        final EditText user= (EditText)findViewById(R.id.user_text);
        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(password.getText().toString().equals("100591622")&&user.getText().toString().equals("arshdeep.benipal")){
                    Intent intent=new Intent();
                    intent.putExtra("MESSAGE","Login Successful");
                    setResult(MainActivity.RESULT_OK,intent);
                    finish();
                }
                else{
                    Intent intent=new Intent();
                    intent.putExtra("MESSAGE","Login Failed");
                    setResult(MainActivity.RESULT_OK,intent);
                    finish();
                };
            }
        });

    }
}
