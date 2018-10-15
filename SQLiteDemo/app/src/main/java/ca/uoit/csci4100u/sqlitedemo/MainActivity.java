package ca.uoit.csci4100u.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import ca.uoit.csci4100u.sqlitedemo.model.Contact;
import ca.uoit.csci4100u.sqlitedemo.model.ContactHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testModel();
    }

    private void testModel() {
        ContactHelper contactHelper = new ContactHelper(this);

        contactHelper.deleteAllContacts();

        Contact bsmith = contactHelper.createContact("Barb",
                "Smith",
                "bsmith@uoit.ca",
                "905-721-8668");

        Contact lramirez = contactHelper.createContact("Luis",
                "Ramirez",
                "lramirez@utoronto.ca",
                "416-123-4567");

        Contact deleteMe = contactHelper.createContact("Tobe",
                "Deleted",
                "deleteme@soon.com",
                "213-888-9999");

        Log.i("SQLite", "getAllContacts():");
        List<Contact> allContacts = contactHelper.getAllContacts();
        for (int i = 0; i < allContacts.size(); i++) {
            Contact current = allContacts.get(i);
            Log.i("SQLite", current.getFirstName() + " " + current.getLastName() +
                    " - " + current.getPhone());
        }

        Contact lramirez2 = contactHelper.getContact(lramirez.getId());
        Log.i("SQLite", "getContact():");
        Log.i("SQLite", lramirez2.getFirstName() + " " + lramirez2.getLastName());

        lramirez2.setPhone("111-222-3333");
        boolean updateSuccess = contactHelper.updateContact(lramirez2);
        Log.i("SQLite", "updateSuccess == " + updateSuccess);

        boolean deleteSuccess = contactHelper.deleteContact(deleteMe.getId());
        Log.i("SQLite", "deleteSuccess == " + deleteSuccess);

        Log.i("SQLite", "getAllContacts():");
        allContacts = contactHelper.getAllContacts();
        for (int i = 0; i < allContacts.size(); i++) {
            Contact current = allContacts.get(i);
            Log.i("SQLite", current.getFirstName() + " " + current.getLastName() +
              " - " + current.getPhone());
        }
    }
}
