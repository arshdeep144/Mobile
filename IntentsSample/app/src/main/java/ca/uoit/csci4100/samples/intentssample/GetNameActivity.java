package ca.uoit.csci4100.samples.intentssample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Randy on 2015-09-18.
 */
public class GetNameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_name);
    }

    public void back(View v) {
        EditText nameField = (EditText)findViewById(R.id.txtName);

        Intent resultIntent = new Intent(Intent.ACTION_PICK);
        resultIntent.putExtra("name", nameField.getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
