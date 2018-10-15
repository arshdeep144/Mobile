package ca.uoit.csci4100.samples.intentssample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    public static int GET_NAME_REQUEST = 4100001;

    private String name = "";
    private String baseURL = "http://www.behindthename.com/name/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void getName(View v) {
        Intent getNameIntent = new Intent(MainActivity.this, GetNameActivity.class);
        startActivityForResult(getNameIntent, GET_NAME_REQUEST);
    }

    public void showOrigin(View v) {
        Uri uri = Uri.parse(baseURL + name.toLowerCase());
        Intent showOriginIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(showOriginIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (responseCode == RESULT_OK) {
            name = resultIntent.getStringExtra("name");
            String greeting = getResources().getString(R.string.greeting_specific) + " " + name;

            TextView lblGreeting = (TextView)findViewById(R.id.lblGreeting);
            lblGreeting.setText(greeting);
        }
    }
}
