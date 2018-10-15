package ca.uoit.csci4100u.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ShowSchoolDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_school_details);

        // get the data for the school to be displayed
        Intent callingIntent = getIntent();
        String name = callingIntent.getStringExtra("name");
        String address = callingIntent.getStringExtra("address");
        String city = callingIntent.getStringExtra("city");
        String postalCode = callingIntent.getStringExtra("postalCode");
        String phone = callingIntent.getStringExtra("phone");

        // initialize the UI components
        TextView nameLabel = (TextView)findViewById(R.id.txtName);
        nameLabel.setText(name);
        TextView addressLabel = (TextView)findViewById(R.id.txtAddress);
        addressLabel.setText(address);
        TextView cityLabel = (TextView)findViewById(R.id.txtCity);
        cityLabel.setText(city);
        TextView postalCodeLabel = (TextView)findViewById(R.id.txtPostalCode);
        postalCodeLabel.setText(postalCode);
        TextView phoneLabel = (TextView)findViewById(R.id.txtPhone);
        phoneLabel.setText(phone);
    }

    public void close(View source) {
        finish();
    }
}
