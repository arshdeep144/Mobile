package ca.uoit.csci4100u.internetresourcesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
                          implements StudentDataObserver {

    public static String url = "http://csundergrad.science.uoit.ca/courses/csci4100u/data/data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void download(View source) {
        Log.i("InternetResourcesDemo", "download()");
        // initiate the download (AsyncTask)
        GetDataTask task = new GetDataTask();
        task.setObserver(this);
        task.execute(new String[] { url });
    }

    public void studentDataUpdated(ArrayList<Student> studentData) {
        // Update the UI
        Log.i("InternetResourcesDemo", studentData.get(0).getFirstName());

        String message = studentData.get(0).getFirstName() + " " + studentData.get(0).getLastName();
        TextView label = (TextView)findViewById(R.id.lblMessage);
        label.setText(message);
    }
}
