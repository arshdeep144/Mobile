package com.example.arshu.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//Since I could not figure out how to create a menu item I was not able to do that part of the assigment
// otherwise it works if the add button is pressed

public class BrowseProducts extends AppCompatActivity implements Observer{
    public TextView txtName;
    public TextView txtDescription;
    public TextView txtPrice;
    public TextView txtPriceBIT;
    private List<Product> product;
    private ProductDBHelper productDBHelper;
    private String message;
    public int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);
        counter = 0;
        productDBHelper = new ProductDBHelper(this);
        product = productDBHelper.getAllProducts();
    }
    //updates UI info with current product info
    public void showProduct(Product product){
        String baseURL= "https://blockchain.info/tobtc?currency=CAD&value=";
        txtName = (TextView)findViewById(R.id.name);
        txtDescription = (TextView)findViewById(R.id.description);
        txtPrice = (TextView)findViewById(R.id.price);
        txtPriceBIT = (TextView)findViewById(R.id.priceBit);
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        txtPrice.setText(Float.toString(product.getPrice()));
        click(baseURL + Float.toString(product.getPrice()));
        txtPriceBIT.setText(message);
    }
    //executes asynctask
    void click(String baseURL){
        DownloadOriginTask task = new DownloadOriginTask();
        task.setObserver(this);
        task.execute(baseURL);
    }
    //stores the bitcoin price
    public void dataUpdated(String data) {
        message = data;
    }
    //adds the new product to the the end of the database
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        ProductDBHelper productDBHelper = new ProductDBHelper(this);
        if (requestCode == RESULT_OK) {
            // store product
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            Float price = data.getFloatExtra("price", -1);
            product.add(productDBHelper.createProduct(name, description, price));
            showProduct(product.get(counter));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //shows next product
    public void nextProduct(View v){
        counter++;
        checkCounter();
        showProduct(product.get(counter));
    }
    //shows previous product
    public void prevProduct(View v){
        counter--;
        checkCounter();
        showProduct(product.get(counter));
    }
    //deletes current product
    public void deleteProduct(View v) {
        int productID = product.get(counter).getProductID();
        productDBHelper.deleteProduct(productID);
        product.remove(counter);
    }

    public void addProduct(View v){
        Intent intent = new Intent(this, AddProduct.class);
        startActivityForResult(intent, RESULT_OK);
    }
    public void checkCounter(){
        if(counter>=product.size()){
            counter=counter%product.size();
        }
        else if(counter<product.size()){
            counter=product.size()-1;
        }
    }
}
