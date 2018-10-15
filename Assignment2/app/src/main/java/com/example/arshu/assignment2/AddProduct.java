package com.example.arshu.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

    public void saveProduct(View v){
        TextView txtName = (EditText)findViewById(R.id.txtName);
        TextView txtDescription = (TextView)findViewById(R.id.txtDescription);
        TextView txtPrice = (TextView)findViewById(R.id.txtPrice);

        Intent intent  = new Intent();
        intent.putExtra("name", txtName.getText().toString());
        intent.putExtra("description", txtDescription.getText().toString());
        intent.putExtra("price", Float.parseFloat(txtPrice.getText().toString()));

        setResult(RESULT_OK, intent);
        finish();
    }
    public void cancelProduct(View v){
        Intent intent  = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
