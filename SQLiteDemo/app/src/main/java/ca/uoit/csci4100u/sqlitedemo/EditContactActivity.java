package ca.uoit.csci4100u.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import ca.uoit.csci4100u.sqlitedemo.model.Contact;
import ca.uoit.csci4100u.sqlitedemo.model.ContactHelper;

public class EditContactActivity extends AppCompatActivity {
    private long id;

    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtEMail;
    private EditText txtPhone;

    private ContactHelper contactHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Intent callingIntent = getIntent();
        id = callingIntent.getLongExtra("id", -1);

        contactHelper = new ContactHelper(this);
        Contact contact = contactHelper.getContact(id);

        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtEMail = (EditText) findViewById(R.id.txtEMail);
        txtPhone = (EditText) findViewById(R.id.txtPhone);

        if (contact != null) {
            txtFirstName.setText(contact.getFirstName());
            txtLastName.setText(contact.getLastName());
            txtEMail.setText(contact.getEmail());
            txtPhone.setText(contact.getPhone());
        }
    }

    private void storeContactData() {
        String firstName = txtFirstName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String email = txtEMail.getText().toString();
        String phone = txtPhone.getText().toString();

        if (id == -1) {
            // add a new contact
            contactHelper.createContact(firstName, lastName, email, phone);
        } else {
            // update the contact
            Contact contact = new Contact(firstName, lastName, email, phone);
            contact.setId(id);

            contactHelper.updateContact(contact);
        }
    }

    private void deleteContact() {
        contactHelper.deleteContact(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuBack) {
            storeContactData();
            finish();
            return true;
        } else if (id == R.id.mnuDelete) {
            deleteContact();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
