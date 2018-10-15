package com.example.arshu.lab06;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShowContacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ShowContacts.this,AddContact.class);
                int resultCode=2;
                startActivityForResult(intent, resultCode);
            }
        });
        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ShowContacts.this,DeleteContact.class);
                int resultCode=3;
                startActivityForResult(intent, resultCode);
            }
        });
    }
    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==ShowContacts.RESULT_OK) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence message = (CharSequence) data.getStringExtra("MESSAGE");
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        }
        else if(requestCode==3){

        }
    }
}
