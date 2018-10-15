package com.example.arshu.lab05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
                          implements Observer{

    public TextView label;
    private String baseURL = "https://www.gnu.org/licenses/gpl.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView license = (TextView)findViewById(R.id.license);
        license.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                click();


            }
        });
    }

    void click(){
        DownloadOriginTask task = new DownloadOriginTask();
        task.setObserver(this);
        task.execute(baseURL);
    }

    public void dataUpdated(String data) {
        // Update the UI
//        label = (TextView)findViewById(R.id.about);
        String message = data;
//        label.setText(message);
        Intent intent = new Intent(MainActivity.this,ShowLicense.class);
        intent.putExtra("info",message);
        startActivity(intent);
    }

}
