package ca.uoit.csci4100u.storageappframeworkdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    static int OPEN_FILE_REQUEST = 01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void showFileContents(String contents) {
        EditText txtContent = (EditText)findViewById(R.id.txtContent);
        txtContent.setText(contents);
    }

    public void browse(View source) {
        // ask SAF to open the file
        Intent openFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        openFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
        openFileIntent.setType("text/*");
        startActivityForResult(openFileIntent, OPEN_FILE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int responseCode,
                                 Intent responseIntent) {
        if ((requestCode == OPEN_FILE_REQUEST) &&
                (responseCode == RESULT_OK) &&
                (responseIntent != null)) {
            StringBuilder content = new StringBuilder();
            try {
                Uri uri = responseIntent.getData();
                InputStream inRaw = getContentResolver().openInputStream(uri);
                BufferedReader in = new BufferedReader(new InputStreamReader(inRaw));
                String line = null;
                while ((line = in.readLine()) != null) {
                    content.append(line + "\n");
                }
                inRaw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            showFileContents(content.toString());
        }
    }
}
