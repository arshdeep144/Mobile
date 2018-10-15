package ca.uoit.csci4100u.sqlitedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.uoit.csci4100u.sqlitedemo.model.Contact;

public class ContactArrayAdapter extends ArrayAdapter<Contact> {

    private List<Contact> data;
    private Context context;

    public ContactArrayAdapter(Context context, List<Contact> data) {
        super(context, R.layout.contact_list_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Contact contact = data.get(position);

        Log.i("SQLite", "Showing - " + contact);

        if (reusableView == null) {
            // create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(context);
            reusableView = inflater.inflate(R.layout.contact_list_item, parent, false);
        }

        TextView lblFirstName = (TextView)reusableView.findViewById(R.id.lblItemFirstName);
        lblFirstName.setText(contact.getFirstName());

        TextView lblLastName = (TextView)reusableView.findViewById(R.id.lblItemLastName);
        lblLastName.setText(contact.getLastName());

        TextView lblEMail = (TextView)reusableView.findViewById(R.id.lblItemEMail);
        lblEMail.setText(contact.getEmail());

        TextView lblPhone = (TextView)reusableView.findViewById(R.id.lblItemPhone);
        lblPhone.setText(contact.getPhone());

        return reusableView;
    }
}
