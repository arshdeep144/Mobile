package ca.uoit.csci4100u.sqlitedemo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "contacts";

    static final String CREATE_STATEMENT = "CREATE TABLE contacts (\n" +
            "      _id integer primary key autoincrement,\n" +
            "      firstName text not null,\n" +
            "      lastName text null,\n" +
            "      email varchar2(100) null,\n" +
            "      phone varchar(25) null\n" +
            ")\n";

    static final String DROP_STATEMENT = "DROP TABLE contacts";

    public ContactHelper(Context context) {
        super(context, "contacts", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database, using CREATE SQL statement
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

    // CRUD functions

    // CREATE
    public Contact createContact(String firstName,
                                 String lastName,
                                 String email,
                                 String phone) {
        // create a new entity object (Contact)
        Contact contact = new Contact(firstName, lastName, email, phone);

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("firstName", firstName);
        newValues.put("lastName", lastName);
        newValues.put("email", email);
        newValues.put("phone", phone);

        long id = db.insert(TABLE, null, newValues);

        // update the contact's id
        contact.setId(id);

        return contact;
    }

    // READ
    public Contact getContact(long id) {
        Contact contact = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "firstName", "lastName", "email", "phone"};
        String where = "_id = ?";
        String[] whereArgs = new String[] { "" + id };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            String email = cursor.getString(3);
            String phone = cursor.getString(4);

            contact = new Contact(firstName, lastName, email, phone);
            contact.setId(id);
        }

        Log.i("SQLite", "createContact(): " + contact);

        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "firstName", "lastName", "email", "phone"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "lastName");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                String email = cursor.getString(3);
                String phone = cursor.getString(4);

                Contact contact = new Contact(firstName, lastName, email, phone);
                contact.setId(id);

                contacts.add(contact);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("SQLite", "getAllContacts(): num = " + contacts.size());

        return contacts;
    }

    // UPDATE
    public boolean updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstName", contact.getFirstName());
        values.put("lastName", contact.getLastName());
        values.put("email", contact.getEmail());
        values.put("phone", contact.getPhone());

        int numRows = db.update(TABLE, values, "_id = ?", new String[] { "" + contact.getId() });

        return (numRows == 1);
    }

    // DELETE
    public boolean deleteContact(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "_id = ?", new String[] { "" + id });

        return (numRows == 1);
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }
}
