package ca.uoit.csci4100u.intentsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class GetNameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        setContentView(R.layout.get_name_activity);

        Intent callingIntent = getIntent();
        String uselessInfo = callingIntent.getStringExtra("unnecessaryData");
        Log.i("IntentsDemo", uselessInfo);
    }

    public void done(View source) {
        EditText nameField = (EditText)findViewById(R.id.txtName);
        String name = nameField.getText().toString();

        Intent resultIntent = new Intent(Intent.ACTION_PICK);
        resultIntent.putExtra("userEnteredName", name);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancel(View source) {
        Intent resultIntent = new Intent(Intent.ACTION_PICK);
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }
}
