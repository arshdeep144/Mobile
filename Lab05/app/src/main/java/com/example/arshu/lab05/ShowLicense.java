package com.example.arshu.lab05;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ShowLicense extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_license);
        Intent intent = getIntent();
        EditText about = (EditText)findViewById(R.id.txtName);
        about.setText(intent.getStringExtra("info"));
        Button close = (Button)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                finish();
            }
        });
    }


}
