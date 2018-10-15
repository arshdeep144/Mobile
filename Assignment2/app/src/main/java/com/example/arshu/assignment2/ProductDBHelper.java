package com.example.arshu.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arshu on 2017-11-15.
 */

public class ProductDBHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "products";

    static final String CREATE_STATEMENT = "CREATE TABLE products (\n" +
            "      productID integer primary key autoincrement,\n" +
            "      name varchar(100) not null,\n" +
            "      description varchar(100) not null,\n" +
            "      price decimal not null);\n";

    static final String DROP_STATEMENT = "DROP TABLE products";

    public ProductDBHelper(Context context){super(context, TABLE, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum) {
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);
    }

    public Product createProduct(String name, String description, float price){
        Product product = new Product(name, description, price);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);
        newValues.put("description", description);
        newValues.put("price", price);
        int studentID = (int) db.insert(TABLE, null, newValues);
        product.setProductID(studentID);

        return product;
    }

    public Product getProduct(int productID){
        Product product = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"productID", "name", "description", "price"};
        String where = "productID = ?";
        String[] whereArgs = new String[] {"" + productID};
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if(cursor.getCount() >= 1){
            cursor.moveToFirst();

            String name = cursor.getString(1);
            String description = cursor.getString(2);
            float price = cursor.getFloat(3);

            product = new Product(name, description, price);
            product.setProductID(productID);
        }
        return product;
    }
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"productID", "name","description", "price"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "name");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                int productID = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                Float price = cursor.getFloat(3);

                Product product = new Product(name,description,  price);
                product.setProductID(productID);

                products.add(product);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());;

        return products;
    }

    // DELETE
    public boolean deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "productID = ?", new String[] { "" + id });

        return (numRows == 1);
    }


}

