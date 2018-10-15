package com.example.arshu.arshdeep_benipal_midterm_labexam;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListSchoolActivity extends AppCompatActivity
                                implements AdapterView.OnItemClickListener {
    public static int GET_NAME_REQUEST = 4100001;

    private String name = "android"; // for now
    private String baseURL = "http://csundergrad.science.uoit.ca/courses/csci4100u/assessments/school_data.csv";
//use sqlite to store the data into the listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_school);
    }

    //When a school is clicked store the data into the school class and send it to teh details
    // activity via putExtra
    @Override
    public void onItemClick(AdapterView aView, View source,
                            int position, long id) {
        School school = new School();
        Intent intent = new Intent(ListSchoolActivity.this, ShowSchoolDetailsActivity.class);
        intent.putExtra("name", school.getName());
        intent.putExtra("city", school.getCity());
        intent.putExtra("address", school.getAddress());
        intent.putExtra("phone", school.getPhone());
        intent.putExtra("postalCode", school.getPostalCode());
    }
//Retrieve the downloaded csv file
//    public void getOrigin(View v) {
//        String url = baseURL;
//        DownloadOriginTask downloadOriginTask = new DownloadOriginTask();
//        Log.d("InternetResourcesSample", "running task: " + url);
//        downloadOriginTask.execute(url);
//    }


//    download the csv file using AsyncTask
//    class DownloadOriginTask extends AsyncTask<String, Void, String> {
//        private Exception exception = null;
//        private String definition = null;
//
//        @Override
//        protected String doInBackground(String... params) {
//            return definition;
//        }
//
//
//        @Override
//        protected void onPostExecute(String result) {
//                return;
//            }
//        }
//    }
}
