package ca.uoit.csci4100u.intentsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int GET_NAME_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getName(View source) {
        Intent getNameIntent = new Intent(this, GetNameActivity.class);
        getNameIntent.putExtra("unnecessaryData", "Mobile Devices!");
        startActivityForResult(getNameIntent, GET_NAME_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int responseCode,
                                 Intent result) {
        Log.i("IntentDemo", "onActivityResult()");

        super.onActivityResult(requestCode,
                               responseCode,
                               result);

        TextView messageField = (TextView)findViewById(R.id.lblMessage);

        if (responseCode == RESULT_CANCELED) {
            String message = getResources().getString(R.string.cancelledMessage);
            messageField.setText(message);
        } else if (responseCode == RESULT_OK) {
            String message = getResources().getString(R.string.greeting);
            message += result.getStringExtra("userEnteredName");
            messageField.setText(message);
        }
    }
}
